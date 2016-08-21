import processing.core.PApplet;

import heronarts.lx.LX;
import heronarts.lx.pattern.LXPattern;

abstract class Pattern extends LXPattern {

  protected final Model model;

  public Pattern(LX lx) {
    super(lx);
    this.model = (Model)super.model;
  }

  public void setLEDColor(LED led, int c) {
    setColor(led.index, c);
  }

  // Processing functions
  // (duplicated here for easy access)

  public static final float PI = Utils.PI;
  public static final float HALF_PI = Utils.HALF_PI;
  public static final float THIRD_PI = Utils.THIRD_PI;
  public static final float QUARTER_PI = Utils.QUARTER_PI;
  public static final float TWO_PI = Utils.TWO_PI;

  //////////////////////////////////////////////////////////////
  // getting the time
  static public int second() { return PApplet.second(); }
  static public int minute() { return PApplet.minute(); }
  static public int hour() { return PApplet.hour(); }
  static public int day() { return PApplet.day(); }
  static public int month() { return PApplet.month(); }
  static public int year() { return PApplet.year(); }

  //////////////////////////////////////////////////////////////
  // printing
  static public void print(byte what) { PApplet.print(what); }
  static public void print(boolean what) { PApplet.print(what); }
  static public void print(char what) { PApplet.print(what); }
  static public void print(int what) { PApplet.print(what); }
  static public void print(long what) { PApplet.print(what); }
  static public void print(float what) { PApplet.print(what); }
  static public void print(double what) { PApplet.print(what); }
  static public void print(String what) { PApplet.print(what); }
  static public void print(Object... variables) { PApplet.print(variables); }
  static public void println() { PApplet.println(); }
  static public void println(byte what) { PApplet.println(what); }
  static public void println(boolean what) { PApplet.println(what); }
  static public void println(char what) { PApplet.println(what); }
  static public void println(int what) { PApplet.println(what); }
  static public void println(long what) { PApplet.println(what); }
  static public void println(float what) { PApplet.println(what); }
  static public void println(double what) { PApplet.println(what); }
  static public void println(String what) { PApplet.println(what); }
  static public void println(Object... variables) { PApplet.println(variables); }
  static public void println(Object what) { PApplet.println(what); }
  static public void printArray(Object what) { PApplet.println(what); }
  static public void debug(String msg) { PApplet.debug(msg); }

  //////////////////////////////////////////////////////////////
  // MATH
  static public final float abs(float n) { return PApplet.abs(n); }
  static public final int abs(int n) { return PApplet.abs(n); }
  static public final float sq(float n) { return PApplet.sq(n); }
  static public final float sqrt(float n) { return PApplet.sqrt(n); }
  static public final float log(float n) { return PApplet.log(n); }
  static public final float exp(float n) { return PApplet.exp(n); }
  static public final float pow(float n, float e) { return PApplet.pow(n, e); }
  static public final int max(int a, int b) { return PApplet.max(a, b); }
  static public final float max(float a, float b) { return PApplet.max(a, b); }
  static public final int max(int a, int b, int c) { return PApplet.max(a, b, c); }
  static public final float max(float a, float b, float c) { return PApplet.max(a, b, c); }
  static public final int max(int[] list) { return PApplet.max(list); }
  static public final float max(float[] list) { return PApplet.max(list); }
  static public final int min(int a, int b) { return PApplet.min(a, b); }
  static public final float min(float a, float b) { return PApplet.min(a, b); }
  static public final int min(int a, int b, int c) { return PApplet.min(a, b, c); }
  static public final float min(float a, float b, float c) { return PApplet.min(a, b, c); }
  static public final int min(int[] list) { return PApplet.min(list); }
  static public final float min(float[] list) { return PApplet.min(list); }
  static public final int constrain(int amt, int low, int high) { return PApplet.constrain(amt, low, high); }
  static public final float constrain(float amt, float low, float high) { return PApplet.constrain(amt, low, high); }
  static public final float sin(float angle) { return PApplet.sin(angle); }
  static public final float cos(float angle) { return PApplet.cos(angle); }
  static public final float tan(float angle) { return PApplet.tan(angle); }
  static public final float asin(float value) { return PApplet.asin(value); }
  static public final float acos(float value) { return PApplet.acos(value); }
  static public final float atan(float value) { return PApplet.atan(value); }
  static public final float atan2(float y, float x) { return PApplet.atan2(y, x); }
  static public final float degrees(float radians) { return PApplet.degrees(radians); }
  static public final float radians(float degrees) { return PApplet.radians(degrees); }
  static public final int ceil(float n) { return PApplet.ceil(n); }
  static public final int floor(float n) { return PApplet.floor(n); }
  static public final int round(float n) { return PApplet.round(n); }
  static public final float mag(float a, float b) { return PApplet.mag(a, b); }
  static public final float mag(float a, float b, float c) { return PApplet.mag(a, b, c); }
  static public final float dist(float x1, float y1, float x2, float y2) { return PApplet.dist(x1, y1, x2, y2); }
  static public final float dist(float x1, float y1, float z1,
                                 float x2, float y2, float z2) { return PApplet.dist(x1, y1, z1, x2, y2, z2); }
  static public final float lerp(float start, float stop, float amt) { return PApplet.lerp(start, stop, amt); }
  static public final float norm(float value, float start, float stop) { return PApplet.norm(value, start, stop); }
  static public final float map(float value,
                                float start1, float stop1,
                                float start2, float stop2) { return PApplet.map(value, start1, stop1, start2, stop2); }

  //////////////////////////////////////////////////////////////
  // SORT
  static public byte[] sort(byte list[]) { return PApplet.sort(list); }
  static public byte[] sort(byte[] list, int count) { return PApplet.sort(list, count); }
  static public char[] sort(char list[]) { return PApplet.sort(list); }
  static public char[] sort(char[] list, int count) { return PApplet.sort(list, count); }
  static public int[] sort(int list[]) { return PApplet.sort(list); }
  static public int[] sort(int[] list, int count) { return PApplet.sort(list, count); }
  static public float[] sort(float list[]) { return PApplet.sort(list); }
  static public float[] sort(float[] list, int count) { return PApplet.sort(list, count); }
  static public String[] sort(String list[]) { return PApplet.sort(list); }
  static public String[] sort(String[] list, int count) { return PApplet.sort(list, count); }

  //////////////////////////////////////////////////////////////
  // ARRAY UTILITIES
  static public void arrayCopy(Object src, int srcPosition,
                               Object dst, int dstPosition,
                               int length) { PApplet.arrayCopy(src, srcPosition, dst, dstPosition, length); }
  static public void arrayCopy(Object src, Object dst, int length) { PApplet.arrayCopy(src, dst, length); }
  static public void arrayCopy(Object src, Object dst) { PApplet.arrayCopy(src, dst); }
  static public boolean[] expand(boolean list[]) { return PApplet.expand(list); }
  static public boolean[] expand(boolean list[], int newSize) { return PApplet.expand(list, newSize); }
  static public byte[] expand(byte list[]) { return PApplet.expand(list); }
  static public byte[] expand(byte list[], int newSize) { return PApplet.expand(list, newSize); }
  static public char[] expand(char list[]) { return PApplet.expand(list); }
  static public char[] expand(char list[], int newSize) { return PApplet.expand(list, newSize); }
  static public int[] expand(int list[]) { return PApplet.expand(list); }
  static public int[] expand(int list[], int newSize) { return PApplet.expand(list, newSize); }
  static public long[] expand(long list[]) { return PApplet.expand(list); }
  static public long[] expand(long list[], int newSize) { return PApplet.expand(list, newSize); }
  static public float[] expand(float list[]) { return PApplet.expand(list); }
  static public float[] expand(float list[], int newSize) { return PApplet.expand(list, newSize); }
  static public double[] expand(double list[]) { return PApplet.expand(list); }
  static public double[] expand(double list[], int newSize) { return PApplet.expand(list, newSize); }
  static public String[] expand(String list[]) { return PApplet.expand(list); }
  static public String[] expand(String list[], int newSize) { return PApplet.expand(list, newSize); }
  static public Object expand(Object array) { return PApplet.expand(array); }
  static public Object expand(Object list, int newSize) { return PApplet.expand(list, newSize); }
  static public byte[] append(byte array[], byte value) { return PApplet.append(array, value); }
  static public char[] append(char array[], char value) { return PApplet.append(array, value); }
  static public int[] append(int array[], int value) { return PApplet.append(array, value); }
  static public float[] append(float array[], float value) { return PApplet.append(array, value); }
  static public String[] append(String array[], String value) { return PApplet.append(array, value); }
  static public Object append(Object array, Object value) { return PApplet.append(array, value); }
  static public boolean[] shorten(boolean list[]) { return PApplet.shorten(list); }
  static public byte[] shorten(byte list[]) { return PApplet.shorten(list); }
  static public char[] shorten(char list[]) { return PApplet.shorten(list); }
  static public int[] shorten(int list[]) { return PApplet.shorten(list); }
  static public float[] shorten(float list[]) { return PApplet.shorten(list); }
  static public String[] shorten(String list[]) { return PApplet.shorten(list); }
  static public Object shorten(Object list) { return PApplet.shorten(list); }
  static final public boolean[] splice(boolean list[],
                                       boolean value, int index) { return PApplet.splice(list, value, index); }
  static final public boolean[] splice(boolean list[],
                                       boolean value[], int index) { return PApplet.splice(list, value, index); }
  static final public byte[] splice(byte list[],
                                    byte value, int index) { return PApplet.splice(list, value, index); }
  static final public byte[] splice(byte list[],
                                    byte value[], int index) { return PApplet.splice(list, value, index); }
  static final public char[] splice(char list[],
                                    char value, int index) { return PApplet.splice(list, value, index); }
  static final public char[] splice(char list[],
                                    char value[], int index) { return PApplet.splice(list, value, index); }
  static final public int[] splice(int list[],
                                   int value, int index) { return PApplet.splice(list, value, index); }
  static final public int[] splice(int list[],
                                   int value[], int index) { return PApplet.splice(list, value, index); }
  static final public float[] splice(float list[],
                                     float value, int index) { return PApplet.splice(list, value, index); }
  static final public float[] splice(float list[],
                                     float value[], int index) { return PApplet.splice(list, value, index); }
  static final public String[] splice(String list[],
                                      String value, int index) { return PApplet.splice(list, value, index); }
  static final public String[] splice(String list[],
                                      String value[], int index) { return PApplet.splice(list, value, index); }
  static final public Object splice(Object list, Object value, int index) { return PApplet.splice(list, value, index); }
  static public boolean[] subset(boolean list[], int start) { return PApplet.subset(list, start); }
  static public boolean[] subset(boolean list[], int start, int count) { return PApplet.subset(list, start, count); }
  static public byte[] subset(byte list[], int start) { return PApplet.subset(list, start); }
  static public byte[] subset(byte list[], int start, int count) { return PApplet.subset(list, start, count); }
  static public char[] subset(char list[], int start) { return PApplet.subset(list, start); }
  static public char[] subset(char list[], int start, int count) { return PApplet.subset(list, start, count); }
  static public int[] subset(int list[], int start) { return PApplet.subset(list, start); }
  static public int[] subset(int list[], int start, int count) { return PApplet.subset(list, start, count); }
  static public float[] subset(float list[], int start) { return PApplet.subset(list, start); }
  static public float[] subset(float list[], int start, int count) { return PApplet.subset(list, start, count); }
  static public String[] subset(String list[], int start) { return PApplet.subset(list, start); }
  static public String[] subset(String list[], int start, int count) { return PApplet.subset(list, start, count); }
  static public Object subset(Object list, int start) { return PApplet.subset(list, start); }
  static public Object subset(Object list, int start, int count) { return PApplet.subset(list, start, count); }
  static public boolean[] concat(boolean a[], boolean b[]) { return PApplet.concat(a, b); }
  static public byte[] concat(byte a[], byte b[]) { return PApplet.concat(a, b); }
  static public char[] concat(char a[], char b[]) { return PApplet.concat(a, b); }
  static public int[] concat(int a[], int b[]) { return PApplet.concat(a, b); }
  static public float[] concat(float a[], float b[]) { return PApplet.concat(a, b); }
  static public String[] concat(String a[], String b[]) { return PApplet.concat(a, b); }
  static public Object concat(Object a, Object b) { return PApplet.concat(a, b); }
  static public boolean[] reverse(boolean list[]) { return PApplet.reverse(list); }
  static public byte[] reverse(byte list[]) { return PApplet.reverse(list); }
  static public char[] reverse(char list[]) { return PApplet.reverse(list); }
  static public int[] reverse(int list[]) { return PApplet.reverse(list); }
  static public float[] reverse(float list[]) { return PApplet.reverse(list); }
  static public String[] reverse(String list[]) { return PApplet.reverse(list); }
  static public Object reverse(Object list) { return PApplet.reverse(list); }

  //////////////////////////////////////////////////////////////
  // STRINGS
  static public String trim(String str) { return PApplet.trim(str); }
  static public String[] trim(String[] array) { return PApplet.trim(array); }
  static public String join(String[] list, char separator) { return PApplet.join(list, separator); }
  static public String join(String[] list, String separator) { return PApplet.join(list, separator); }
  static public String[] splitTokens(String value) { return PApplet.splitTokens(value); }
  static public String[] splitTokens(String value, String delim) { return PApplet.splitTokens(value, delim); }
  static public String[] split(String value, char delim) { return PApplet.split(value, delim); }
  static public String[] split(String value, String delim) { return PApplet.split(value, delim); }
  static public String[] match(String str, String regexp) { return PApplet.match(str, regexp); }
  static public String[][] matchAll(String str, String regexp) { return PApplet.matchAll(str, regexp); }

  //////////////////////////////////////////////////////////////
  // CASTING FUNCTIONS
  static final public boolean parseBoolean(int what) { return PApplet.parseBoolean(what); }
  static final public boolean parseBoolean(String what) { return PApplet.parseBoolean(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public boolean[] parseBoolean(int what[]) { return PApplet.parseBoolean(what); }
  static final public boolean[] parseBoolean(String what[]) { return PApplet.parseBoolean(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public byte parseByte(boolean what) { return PApplet.parseByte(what); }
  static final public byte parseByte(char what) { return PApplet.parseByte(what); }
  static final public byte parseByte(int what) { return PApplet.parseByte(what); }
  static final public byte parseByte(float what) { return PApplet.parseByte(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public byte[] parseByte(boolean what[]) { return PApplet.parseByte(what); }
  static final public byte[] parseByte(char what[]) { return PApplet.parseByte(what); }
  static final public byte[] parseByte(int what[]) { return PApplet.parseByte(what); }
  static final public byte[] parseByte(float what[]) { return PApplet.parseByte(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public char parseChar(byte what) { return PApplet.parseChar(what); }
  static final public char parseChar(int what) { return PApplet.parseChar(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public char[] parseChar(byte what[]) { return PApplet.parseChar(what); }
  static final public char[] parseChar(int what[]) { return PApplet.parseChar(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public int parseInt(boolean what) { return PApplet.parseInt(what); }
  static final public int parseInt(byte what) { return PApplet.parseInt(what); }
  static final public int parseInt(char what) { return PApplet.parseInt(what); }
  static final public int parseInt(float what) { return PApplet.parseInt(what); }
  static final public int parseInt(String what) { return PApplet.parseInt(what); }
  static final public int parseInt(String what, int otherwise) { return PApplet.parseInt(what, otherwise); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public int[] parseInt(boolean what[]) { return PApplet.parseInt(what); }
  static final public int[] parseInt(byte what[]) { return PApplet.parseInt(what); }
  static final public int[] parseInt(char what[]) { return PApplet.parseInt(what); }
  static public int[] parseInt(float what[]) { return PApplet.parseInt(what); }
  static public int[] parseInt(String what[]) { return PApplet.parseInt(what); }
  static public int[] parseInt(String what[], int missing) { return PApplet.parseInt(what, missing); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public float parseFloat(int what) { return PApplet.parseFloat(what); }
  static final public float parseFloat(String what) { return PApplet.parseFloat(what); }
  static final public float parseFloat(String what, float otherwise) { return PApplet.parseFloat(what, otherwise); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public float[] parseFloat(byte what[]) { return PApplet.parseFloat(what); }
  static final public float[] parseFloat(int what[]) { return PApplet.parseFloat(what); }
  static final public float[] parseFloat(String what[]) { return PApplet.parseFloat(what); }
  static final public float[] parseFloat(String what[], float missing) { return PApplet.parseFloat(what, missing); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public String str(boolean x) { return PApplet.str(x); }
  static final public String str(byte x) { return PApplet.str(x); }
  static final public String str(char x) { return PApplet.str(x); }
  static final public String str(int x) { return PApplet.str(x); }
  static final public String str(float x) { return PApplet.str(x); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  static final public String[] str(boolean x[]) { return PApplet.str(x); }
  static final public String[] str(byte x[]) { return PApplet.str(x); }
  static final public String[] str(char x[]) { return PApplet.str(x); }
  static final public String[] str(float x[]) { return PApplet.str(x); }

  //////////////////////////////////////////////////////////////
  // INT NUMBER FORMATTING
  static public String nf(float num) { return PApplet.nf(num); }
  static public String[] nf(float[] num) { return PApplet.nf(num); }
  static public String[] nf(int num[], int digits) { return PApplet.nf(num, digits); }
  static public String nf(int num, int digits) { return PApplet.nf(num, digits); }
  static public String[] nfc(int num[]) { return PApplet.nfc(num); }
  static public String nfc(int num) { return PApplet.nfc(num); }
  static public String nfs(int num, int digits) { return PApplet.nfs(num, digits); }
  static public String[] nfs(int num[], int digits) { return PApplet.nfs(num, digits); }
  static public String nfp(int num, int digits) { return PApplet.nfp(num, digits); }
  static public String[] nfp(int num[], int digits) { return PApplet.nfp(num, digits); }

  //////////////////////////////////////////////////////////////
  // FLOAT NUMBER FORMATTING
  static public String[] nf(float num[], int left, int right) { return PApplet.nf(num, left, right); }
  static public String nf(float num, int left, int right) { return PApplet.nf(num, left, right); }
  static public String[] nfc(float num[], int right) { return PApplet.nfc(num, right); }
  static public String nfc(float num, int right) { return PApplet.nfc(num, right); }
  static public String[] nfs(float num[], int left, int right) { return PApplet.nfs(num, left, right); }
  static public String nfs(float num, int left, int right) { return PApplet.nfs(num, left, right); }
  static public String[] nfp(float num[], int left, int right) { return PApplet.nfp(num, left, right); }
  static public String nfp(float num, int left, int right) { return PApplet.nfp(num, left, right); }

  //////////////////////////////////////////////////////////////
  // HEX/BINARY CONVERSION
  static final public String hex(byte value) { return PApplet.hex(value); }
  static final public String hex(char value) { return PApplet.hex(value); }
  static final public String hex(int value) { return PApplet.hex(value); }
  static final public String hex(int value, int digits) { return PApplet.hex(value, digits); }
  static final public int unhex(String value) { return PApplet.unhex(value); }
  static final public String binary(byte value) { return PApplet.binary(value); }
  static final public String binary(char value) { return PApplet.binary(value); }
  static final public String binary(int value) { return PApplet.binary(value); }
  static final public String binary(int value, int digits) { return PApplet.binary(value, digits); }
  static final public int unbinary(String value) { return PApplet.unbinary(value); }

  //////////////////////////////////////////////////////////////
  // COLOR FUNCTIONS
  static public int blendColor(int c1, int c2, int mode) { return PApplet.blendColor(c1, c2, mode); }
  static public int lerpColor(int c1, int c2, float amt, int mode) { return PApplet.lerpColor(c1, c2, amt, mode); }

}
