#!/usr/bin/ruby
# encoding: utf-8

require 'rubygems'
require 'json'
require 'pp'


#p Encoding.find("filesystem")

#file = File.read('api.txt', :encoding => 'utf-8')
#file = File.open('json.txt', :encoding => 'utf-8')
new_file = File.open("newjson.txt", 'w+') 

#pp file

lines = []
#File.open("json.txt").each do |line|
#  lines.push line
#end
#new_file.write lines
#
#
#file_content = File.open('json.txt','r:UTF-8',&:read)
#pp file_content.read

pattern = /[a-zA-z]+[0-9]?:/

File.foreach("api.txt").with_index do |line, line_num|
  new_line = line
  if(line.include?(":"))
    #key_arr = line.split(":")
    #i = 0
    #key_arr.each do |key|
    #  if(i % 2 == 0)
    #    pattern = key.strip
    #    puts pattern
    #    new_line = line.gsub("/#{pattern}/", "\'#{pattern}\'")
    #  end
    #  i = i + 1
    #end
    new_line = new_line.gsub("\"", "").gsub(/'/, "\"")
    line.scan(pattern).each do |key_word|
      new_line = new_line.gsub(/#{key_word}/, "\"#{key_word.slice(0..-2)}\":")
    end
  end
  puts new_line
end


