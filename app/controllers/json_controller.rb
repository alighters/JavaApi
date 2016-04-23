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
    unless date.nil?
      date = Date.today.to_s
    end
    json = JsonFormat.save_remote_file_to_json (date)
    @data = JsonFormat.parse_json_to_classes json
  end

  def write
    data = JsonGenerator.get_java_classes
    data.each do |java_file|
      java_file.write_to_file
    end
    render :json => {'data' => data}
  end

  def save
    JsonFormat.save_remote_file
    render :json => {'result' => 'ok'}
  end
  
   # 读取远程最新的js文件
  def read_remote
    JsonFormat.save_remote_file
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
    unless @date_now.nil?
      @date_now = Date.today.to_s
    end
    @date_before = '2016-04-21'
    @date_now = '2016-04-22'
    @before_classes = JsonFormat.parse_json_from_file @date_before
    @now_classes = JsonFormat.parse_json_from_file @date_now
    
  end

end
