

import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.runtime.Helpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.RubyClass;


public class CashDispenser extends RubyObject  {
    private static final Ruby __ruby__ = Ruby.getGlobalRuntime();
    private static final RubyClass __metaclass__;

    static {
        String source = new StringBuilder("class BillAcceptor\n" +
            "  @@counts = {:one => 0, :five => 0, :ten => 0, :twenty => 0, :fifty => 0, :hundred => 0}\n" +
            "  @@inserted = []\n" +
            "  \n" +
            "  def hold bill\n" +
            "    @@inserted << bill\n" +
            "  end\n" +
            "\n" +
            "  def reject\n" +
            "    @@inserted = []\n" +
            "  end\n" +
            "\n" +
            "  def stack bill\n" +
            "    @@inserted = []\n" +
            "    @@counts[bill] +=1\n" +
            "    puts current_state \n" +
            "  end\n" +
            "\n" +
            "  def current_state\n" +
            "    @@counts\n" +
            "  end\n" +
            "end\n" +
            "\n" +
            "class CashDispenser\n" +
            "  @@counts = {:one => 6, :five => 12, :ten => 13, :fifty => 21}\n" +
            "\n" +
            "  def dispense bill\n" +
            "    @@counts[bill] -= 1\n" +
            "    puts current_state \n" +
            "  end\n" +
            "\n" +
            "  def current_state\n" +
            "    @@counts\n" +
            "  end\n" +
            "end\n" +
            "\n" +
            "").toString();
        __ruby__.executeScript(source, "main.rb");
        RubyClass metaclass = __ruby__.getClass("CashDispenser");
        if (metaclass == null) throw new NoClassDefFoundError("Could not load Ruby class: CashDispenser");
        metaclass.setRubyStaticAllocator(CashDispenser.class);
        __metaclass__ = metaclass;
    }

    /**
     * Standard Ruby object constructor, for construction-from-Ruby purposes.
     * Generally not for user consumption.
     *
     * @param ruby The JRuby instance this object will belong to
     * @param metaclass The RubyClass representing the Ruby class of this object
     */
    private CashDispenser(Ruby ruby, RubyClass metaclass) {
        super(ruby, metaclass);
    }

    /**
     * A static method used by JRuby for allocating instances of this object
     * from Ruby. Generally not for user comsumption.
     *
     * @param ruby The JRuby instance this object will belong to
     * @param metaclass The RubyClass representing the Ruby class of this object
     */
    public static IRubyObject __allocate__(Ruby ruby, RubyClass metaClass) {
        return new CashDispenser(ruby, metaClass);
    }

    /**
     * Default constructor. Invokes this(Ruby, RubyClass) with the classloader-static
     * Ruby and RubyClass instances assocated with this class, and then invokes the
     * no-argument 'initialize' method in Ruby.
     */
    public CashDispenser() {
        this(__ruby__, __metaclass__);
        Helpers.invoke(__ruby__.getCurrentContext(), this, "initialize");
    }


    
    public Object dispense(Object bill) {
        IRubyObject ruby_arg_bill = JavaUtil.convertJavaToRuby(__ruby__, bill);
        IRubyObject ruby_result = Helpers.invoke(__ruby__.getCurrentContext(), this, "dispense", ruby_arg_bill);
        return (Object)ruby_result.toJava(Object.class);

    }

    
    public Object current_state() {

        IRubyObject ruby_result = Helpers.invoke(__ruby__.getCurrentContext(), this, "current_state");
        return (Object)ruby_result.toJava(Object.class);

    }

}
