class JsonFormat

  require 'rubygems'
  require 'json'
  require 'pp'
  require 'open-uri'
  require 'fileutils'

  @queue = :json_format

  def self.perform
    data = JsonFormat.save_remote_file
    data = JsonFormat.read_remote_file
    JsonFormat.write_json data
  end


  def self.format_local_file
    new_file = File.open("newjson.txt", 'w+') 


    lines = []

    pattern = /[a-zA-z]+[0-9]?:/

    File.foreach("api.txt").with_index do |line, line_num|
      new_line = line
      if(line.include?(":"))
        new_line = new_line.gsub("\"", "").gsub(/'/, "\"")
        line.scan(pattern).each do |key_word|
          new_line = new_line.gsub(/#{key_word}/, "\"#{key_word.slice(0..-2)}\":")
        end
      end
      puts new_line
    end

  end

  def self.save_remote_file
    remote_file = "https://raw.githubusercontent.com/EricOrYhj/MD.API.Test/master/src/app/components/apiSetting2.js"
    cur_dir = Rails.root.to_s + "/output"
    unless Dir.exist? cur_dir
      FileUtils.mkdir_p cur_dir
    end
    file_name = cur_dir + "/" + Date.today.to_s + "-api.txt"
    open(file_name, "wb") do | file|
      open(remote_file) do |uri|
        file.write uri.read
      end
    end

  end

  def self.read_remote_file
    lines = []
    pattern = /[a-zA-z]+[0-9]?:/
    remote_file = Rails.root.to_s + "/output/" + Date.today.to_s + "-api.txt"
    if File.exists? remote_file
      json_start = false
      open(remote_file){ |f|
        f.each_line { |line| 
          if /function apiSetting/ =~ line
            lines.delete_at(lines.size-1)
            lines.delete_at(lines.size-1)
            lines.push '}'
            break
          elsif /var apiParam/ =~ line
            lines.push '{'
            json_start = true
          elsif json_start
            new_line = line.gsub("\"", "").gsub(/'/, "\"")
            new_line.scan(pattern).each do |key_word|
              new_line = new_line.gsub(/#{key_word}/, "\"#{key_word.slice(0..-2)}\":")
            end
            lines.push new_line
          end

        }
      }
    end
    lines
  end

  # def self.save_remote_file_to_json
  #   lines = []
  #   pattern = /[a-zA-z]+[0-9]?:/
  #   remote_file = Rails.root.to_s + "/output/" + Date.today.to_s + "-api.txt"
  #   save_file = Rails.root.to_s + "/output/" + Date.today.to_s + "-json.txt"
  #   file = File.new(save_file, 'w+')
  #   if File.exists? remote_file
  #     json_start = false
  #     open(remote_file){ |f|
  #       f.each_line { |line| 
  #         if /function apiSetting/ =~ line
  #           lines.delete_at(lines.size-1)
  #           lines.delete_at(lines.size-1)
  #           lines.push '}'
  #           break
  #         elsif /var apiParam/ =~ line
  #           lines.push '{'
  #           json_start = true
  #         elsif json_start
  #           new_line = line.gsub("\"", "").gsub(/'/, "\"")
  #           new_line.scan(pattern).each do |key_word|
  #             new_line = new_line.gsub(/#{key_word}/, "\"#{key_word.slice(0..-2)}\":")
  #           end
  #           puts new_line
  #           file.write new_line
  #           lines.push new_line
  #         end

  #       }
  #     }
  #   end
  #   lines
  # end

  def self.save_remote_file_to_json
    lines = []
    pattern = /[a-zA-z]+[0-9]?:/
    remote_file = Rails.root.to_s + "/output/" + Date.today.to_s + "-api.txt"
    save_file = Rails.root.to_s + "/output/" + Date.today.to_s + "-json.txt"
    file = File.new(save_file, 'w+')
    if File.exists? remote_file
      json_start = false
      json_start_line = 0
      json_end_line = 0
      File.foreach(remote_file).with_index do |line, line_num|
        if /function apiSetting/ =~ line
          json_end_line = line_num
        elsif /var apiParam/ =~ line
          json_start_line = line_num
        end
        new_line = line.gsub("\"", "").gsub(/'/, "\"")
        new_line.scan(pattern).each do |key_word|
          new_line = new_line.gsub(/#{key_word}/, "\"#{key_word.slice(0..-2)}\":")
        end
        lines.push new_line
      end

      # 修整数据
      puts lines.size
      puts json_end_line
      last_size = lines.size - json_end_line
      while last_size > 0
        lines.pop
        last_size = last_size - 1
      end
      if json_start_line > 0
        lines.shift json_start_line
        lines[0] = '{'
      end
      lines[ lines.size - 2] = '}'

    end
    json = ""
    lines.each do |line|
      json = json + line
    end
    self.write_json lines
    json
  end

  def self.parse_json_to_classes (json)
    classes = []
    ob = JSON.parse json

    ob.each { |model_key, model_value|
      model_value.each { |version_key, version_value|

        values = version_value.values

        if(!values[0].nil? && is_method_json?(values[0]))
          java_class = JavaClass.new
          java_class.name = model_key
          java_class.version = version_key
          java_class.set_methods (version_value)
          classes.push java_class
        else
          # 处理admin/private这种情况，另一种module的代码
          version_value.each { |api_key, api_value|
            java_class = JavaClass.new
            java_class.name = api_key
            java_class.version = version_key
            java_class.node = model_key
            java_class.set_methods (api_value)
            classes.push java_class
          }
        end
      }
    }
    classes

  end


  def self.format_to_json(lines)
    pattern = /[a-zA-z]+[0-9]?:/
    new_lines = []
    lines.each do |line|
      new_line = line
      if(line.include?(":"))
        new_line = new_line.gsub("\"", "").gsub(/'/, "\"")
        new_line.scan(pattern).each do |key_word|
          new_line = new_line.gsub(/#{key_word}/, "\"#{key_word.slice(0..-2)}\":")
        end
      end
      new_lines.push new_line
    end
    new_lines
  end

  def self.write_json(lines)
    cur_dir = Rails.root.to_s + "/output"
    unless Dir.exist? cur_dir
      FileUtils.mkdir_p cur_dir
    end
    file_name = Date.today.to_s + "-json.txt"
    file = File.new(cur_dir + "/" + file_name, "w+") 
    lines.each_with_index.map{ |line, index|
      file.write line
    }
    puts lines

  end

   # 检验json格式是否为Java方法的json
  def self.is_method_json? (json)
    result = false
    unless json.nil? 
      if (!json['url'].nil? && !json['name'].nil?)
        result = true
      end
    end
    result
  end

end
