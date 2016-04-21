class JsonController < ApplicationController
  def format
  end

  def read_local
    data = JsonGenerator.get_java_classes
    data.each do |java_file|
      java_file.write_to_file
    end
    render :json => {'data' => data}
  end

  def show
    @data = JsonGenerator.get_java_classes
  end

  def write
    data = JsonGenerator.get_java_classes
    data.each do |java_file|
      java_file.write_to_file
    end
    render :json => {'data' => data}
  end
end
