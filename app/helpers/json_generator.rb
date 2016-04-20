class JsonGenerator

  require 'json'


  def self.to_model
    file = Rails.root.to_s + "/" + "newjson.txt";
    json = File.read(file, :encoding => 'utf-8')
    ob = JSON.parse json

    ob.each { |model_key, model_value|
      javaClass = JavaClass.new
      javaClass.name = model_key
      javaClass.set_methods model_value
      if javaClass.is_valid?
        puts javaClass.to_java
      end
    }
  end

  def self.get_java_classes
    classes = []
    file = Rails.root.to_s + "/" + "newjson.txt";
    json = File.read(file, :encoding => 'utf-8')
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
