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
    Util.to_camel(@key)
  end
  

end
