public enum Roman {
    C(100), L(50), X(10), V(5), I(1);

    final int arabic;

    Roman(int arabic) {
        this.arabic = arabic;
    }

    public int getArabic() {
        return arabic;
    }
}
