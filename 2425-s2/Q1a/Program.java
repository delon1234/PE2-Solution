abstract class Program<T> {
  public abstract T get();

  public abstract boolean isResult();
  
  public static <T> Program<T> result(T value) {
    return new Result<>(value);
  }
  
  private static class Result<T> extends Program<T> {
    private T value;

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
  }
}
