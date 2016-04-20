class JsonController < ApplicationController
  def format
  end

  def read
    data = JsonGenerator.get_java_classes
    data.each do |java_file|
      java_file.write_to_file
    end
    render :json => {'data' => data}
  end
end
