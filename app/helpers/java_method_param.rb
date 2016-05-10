class JavaMethodParam
  attr_accessor :key,  :isMust, :type, :des

  def set_value (json)
    @key = json['key']
    @isMust = json['isMust']
    @type = json['type']
    @des = json['des']
  end
  
  def to_java
    "@Query(\"#{key}\") #{get_type} #{get_key}"
  end

  def get_type
    type = "int"
    case @type
    when "string"
      type = "String"
    when "bool"
      type = "Boolean"
    when "int64"
      type = "long"
    end
    type
  end

  def get_key
    key = Util.to_camel(@key)
    if 'pageindex' == key
      key = 'pageIndex'
    elsif 'pagesize' == key
      key = 'pageSize'
    end
    key
  end
  
  def == (param)
    result = true
    unless @key.eql? param.key
      result = false
    end
    unless @type.eql? param.type
      result = false
    end
    unless @isMust == param.isMust
      result = false
    end
    result
  end

end
