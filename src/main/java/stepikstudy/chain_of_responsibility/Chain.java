package stepikstudy.chain_of_responsibility;
public interface Chain {
    public abstract void setNext(Chain nextInChain);
    public abstract void process(Number request);
}