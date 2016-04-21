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
 
   def self.save_remote_file_to_json
    lines = []
    pattern = /[a-zA-z]+[0-9]?:/
    remote_file = Rails.root.to_s + "/output/" + Date.today.to_s + "-api.txt"
    save_file = Rails.root.to_s + "/output/" + Date.today.to_s + "-json.txt"
    file = File.new(save_file, 'w+')
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
            puts new_line
            file.write new_line
            lines.push new_line
          end

        }
      }
    end
    lines
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

end
