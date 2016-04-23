class JavaMethod

  attr_accessor :name, :docUrl, :url, :requestMode ,:url_prefix
  attr_accessor :params

  @intent_space = "    "

  def initialize
    self.params = []
  end

  def set_value(json = nil, url_prefix = nil)
    @name = json['name']
    @docUrl = json['docUrl']
    @url = json['url']
    @url_prefix = url_prefix 
    @requestMode = json['requestMode']
    unless json['params'].nil?
      json['params'].each do | param|
        method = JavaMethodParam.new
        method.set_value(param)
        @params.push method
      end
    end
  end

  def to_java
    values = []
    values.push  "/**"
    values.push  " * #{@name}"
    values.push  " *"
    @params.each do |method_param|
      str = ""
      if method_param.isMust
        str = "必填字段"
      end
      values.push " * @param #{method_param.get_key}    #{str}  #{method_param.des}"
    end
    values.push " **/"

    values.push "@#{@requestMode.to_s.upcase}(\"#{get_url}\")"

    values.push "Observable<Void> #{get_method_name}("
    @params.each_with_index.map{|method_param, i|
      if i < @params.size - 1
        values.push "    #{method_param.to_java},"
      else
        values.push "    #{method_param.to_java});"
      end
    }
    values
  end

  def to_java_str
    str = ""
    to_java.each do |code|
      str = str + code.to_s + "\r\n"
    end
    str
  end


  def get_method_name
    method_name = nil
    values = []
    unless @url.nil?
      last_name = @url.split('/').last
      method_name = Util.to_camel(last_name)
    end
    method_name
  end

  def get_url
    url = @url
    unless @url_prefix.nil?
      url = "/#{@url_prefix}#{@url}"
    end
    url 
  end

  def == (method)
    result = true
    unless @url == method.url
      result = false
    end
    result 
  end

  def is_not_changed? method
    result = true
    unless @name.eql? method.name
      result = false
    end
    unless @requestMode.eql? method.requestMode
      result = false
    end
    unless @url_prefix.eql? method.url_prefix
      result = false
    end
    unless @params == method.params
      result = false
    end
    result
  end

end
