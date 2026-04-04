abstract class Program<T> {
  public abstract T get();

  public abstract boolean isResult();

  public abstract <U> Program<U> flatMap(Transformer<? super T, ? extends Program<? extends U>> f);

  public static <T> Program<T> result(T value) {
    return new Result<>(value);
  }

  public static <U, T> Program<U> chain(
      Program<? extends T> prog, Transformer<? super T, ? extends Program<? extends U>> f) {
    return new Chain<>(prog, f);
  }

  private static final class Result<T> extends Program<T> {
    private final T value;

    public Result(T value) {
      this.value = value;
    }

    @Override
    public boolean isResult() {
      return true;
    }

    @Override
    public T get() {
      return this.value;
    }

    @Override
    public <U> Program<U> flatMap(Transformer<? super T, ? extends Program<? extends U>> f) {
      return new Chain<T, U>(this, f);
    }
  }

  private static final class Chain<T, U> extends Program<U> {
    private final Program<? extends T> prog;
    private final Transformer<? super T, ? extends Program<? extends U>> f;

    public Chain(
        Program<? extends T> prog, Transformer<? super T, ? extends Program<? extends U>> f) {
      this.prog = prog;
      this.f = f;
    }

    @Override
    public U get() {
      return this.f.transform(this.prog.get()).get();
    }

    @Override
    public <R> Program<R> flatMap(Transformer<? super U, ? extends Program<? extends R>> f) {
      return new Chain<>(prog, x -> this.f.transform(x).flatMap(f));
    }

    @Override
    public boolean isResult() {
      return false;
    }
  }
}
