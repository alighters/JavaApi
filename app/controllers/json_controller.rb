class JsonController < ApplicationController


  # 显示所有的版本文件
  def index
    dir_name = Rails.root.to_s + "/output"
    @files = []
    Dir.glob("#{dir_name}/*").each do |file|
      @files.push file.to_s.split('/').last
    end
  end

  # 根据日期显示相应版本所有的java接口数据
  def show
    date = params[:date]
    if date.nil?
      date = Date.today.to_s
    end
    @data = JsonFormat.parse_json_from_file (date)
  end

  def write
    JsonFormat.save_remote_file
    json = JsonFormat.save_remote_file_to_json Date.today.to_s
    data = JsonFormat.parse_json_to_classes json
    data.each do |java_class|
      java_class.write_to_file
    end

    render :json => {'data' => 'ok'}
  end

  def save
    JsonFormat.save_remote_file
    render :json => {'result' => 'ok'}
  end

  def delete
    date = params[:date]

    if date.nil?
      render :json => {'error' => 'Date is not allowed empty'}
    else
      dir_name = Rails.root.to_s + "/output/" + date.to_s
      FileUtils.rm_r Dir.glob("#{dir_name}")
      render :json => {'result' => 'ok'}
    end

  end
  
   # 读取远程最新的js文件
  def read_remote
    JsonFormat.save_remote_file
    JsonFormat.save_remote_file_to_json Date.today.to_s
    render :json => {'result' => 'ok'}
  end

  def test
    #data = JsonFormat.read_remote_file
    #JsonFormat.write_json data
    data = JsonFormat.save_remote_file_to_json
    render :json => {'data' => data}
  end

  def diff
    @date_before = params[:before]
    @date_now = params[:now]
    if @date_now.nil?
      @date_now = Date.today.to_s
    end
    @before_classes = JsonFormat.parse_json_from_file @date_before
    @now_classes = JsonFormat.parse_json_from_file @date_now
    
  end

end
