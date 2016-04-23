class JavaClass
 
  attr_accessor :package, :name, :node, :version
  attr_accessor :methods

  require 'fileutils'


  attr_accessor :methods_deleted
  attr_accessor :methods_updated
  attr_accessor :methods_added


  def initialize()
    self.methods = []
    self.methods_updated = []
    self.methods_deleted = []
    self.methods_added = []
    self.package = "com.mingdao.data.net"
  end

  def set_methods(methods_json)
    methods_json.each{ |method_key, method_value|
      set_method_value(method_value)
    }
  end

  def set_method_value(method_json)
    if is_method_json?(method_json)
      javaMethod = JavaMethod.new
      javaMethod.set_value(method_json, @version)
      @methods.push javaMethod
    end

  end

  def to_java
    values = []
    values.push "package " + get_package + ";"
    values.push ""
    values += get_import_array
    values.push ""
    values.push "public interface #{get_name} {"

    values = values + to_java_method

    values.push "}"
    values 
  end

  def to_java_str
    str = ""
    to_java.each do |code|
      str = str + code.to_s + "\r\n"
    end
    str
  end

  def to_java_method
    values = []
    @methods.each_with_index.map{ |method, i|
      method.to_java.each do |method_value|
        values.push "    #{method_value.to_s}"
      end
      if i < @methods.size - 1
        values.push ""
      end
    }
    values 
  end

  def get_import_array
    imports = []
    imports.push "import retrofit2.http.GET;"
    imports.push "import retrofit2.http.POST;"
    imports.push "import retrofit2.http.Query;"
    imports.push "import rx.Observable;"
    imports
  end

  def is_valid?
    if @methods.size == 0
      return false
    end
    true
  end


  # 检验json格式是否为Java方法的json
  def is_method_json? (json)
    result = false
    unless json.nil? 
      if (!json['url'].nil? && !json['name'].nil?)
        result = true
      end
    end
    result
  end

  def write_to_file
    dir_name = Rails.root.to_s + "/java/api/"
    unless @node.nil?
      dir_name = dir_name + "/#{@node.to_s}"
    end
    unless @name.nil?
      dir_name = dir_name + "/#{@name.to_s}"
    end
    unless Dir.exist? dir_name
      FileUtils.mkdir_p dir_name
    end
    file_name = dir_name + "/#{get_name}.java"
    if !File.exist?(file_name)

      file = File.new(file_name, "w+")
      to_java.each do |line|
        file.write line + "\n"
      end
      file.close
    else
      lines = []
      File.open(file_name).each do |line|
        lines.push line
      end
      File.delete(file_name)
      file = File.new(file_name, "w+")
      lines.each_with_index.map{ |line, index|
        if index == lines.size - 1 
          to_java_method.each do |method_line|
            file.write method_line + "\n"
          end
        end
        file.write line + "\n"
      }
    end

  end


  def get_name
    "I#{@name.to_s.capitalize}Service"
  end

  def get_package
    package = @package.to_s
    unless @node.nil?
      package = package + ".#{@node.to_s}"
    end
    unless @name.nil?
      package = package + ".#{@name.to_s}"
    end
    package
  end

  def == (another)
    unless @name == another.name
      return false
    end
    unless @node == another.node
      return false
    end
    unless @package == another.package
      return false
    end
    unless @version == another.version
      return false
    end
    return true
  end

  def is_methods_diff(another_methods)
    result = false
    @methods.each do |method|
      index = another_methods.find_index method
      if !index.nil?
        if !method.is_not_changed? another_methods[index]
          @methods_updated.push another_methods[index]
          @methods_updated.push method
          result = true
        end
      else
        @methods_added.push method
        result = true
      end
    end

    another_methods.each do |method|
      unless @methods.include? method
        @methods_deleted.push method
        result = true
      end
    end

    result
  end


end
