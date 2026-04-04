class Fruit {
  private Named nameFunc;
  private Perishable expiryFunc;

  public Fruit(Named nameFunc, Perishable expiryFunc) {
    this.nameFunc = nameFunc;
    this.expiryFunc = expiryFunc;
  }

  public String getName() {
    return nameFunc.getName();
  }

  public int getDaysToExpiry() {
    return expiryFunc.getDaysToExpiry();
  }

  @Override
  public String toString() {
    return getName() + " (expires in " + getDaysToExpiry() + " days)";
  }
}
