public class MathService {

    private int init;

    private PlusComponent plus;

    private MinusComponent minus;

    {
        this.plus = new PlusComponent();
        this.minus = new MinusComponent();
    }

    public MathService(final int init) {
        this.init = init;
    }

    public MathService sum(final int addend) {
        this.init = plus.run(this.init, addend);
        return this;
    }

    public MathService subtract(final int subtrahend) {
        this.init -= minus.run(this.init, subtrahend);
        return this;
    }

    public int result() {
        return this.init;
    }
}
