class Util

  def self.to_camel(str)
    new_str = ""
    unless str.nil?
      new_str = str.split('_').map.with_index{|str, i|
        i==0? str: str.capitalize
      }.join
    end
    new_str
  end

end
