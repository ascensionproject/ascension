import processing.core.PApplet;
import processing.core.PConstants;

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

  public static final float EPSILON = PConstants.EPSILON;
  public static final float MAX_FLOAT = PConstants.MAX_FLOAT;
  public static final float MIN_FLOAT = PConstants.MIN_FLOAT;
  public static final int MAX_INT = PConstants.MAX_INT;
  public static final int MIN_INT = PConstants.MIN_INT;

  // shapes
  public static final int VERTEX = PConstants.VERTEX;
  public static final int BEZIER_VERTEX = PConstants.BEZIER_VERTEX;
  public static final int QUADRATIC_VERTEX = PConstants.QUADRATIC_VERTEX;
  public static final int CURVE_VERTEX = PConstants.CURVE_VERTEX;
  public static final int BREAK = PConstants.BREAK;

  // useful goodness
  public static final float PI = PConstants.PI;
  public static final float HALF_PI = PConstants.HALF_PI;
  public static final float THIRD_PI = PConstants.THIRD_PI;
  public static final float QUARTER_PI = PConstants.QUARTER_PI;
  public static final float TWO_PI = PConstants.TWO_PI;
  public static final float TAU = PConstants.TAU;

  public static final float DEG_TO_RAD = PConstants.DEG_TO_RAD;
  public static final float RAD_TO_DEG = PConstants.RAD_TO_DEG;

  public static final String WHITESPACE = PConstants.WHITESPACE;

  // for colors and/or images
  public static final int RGB   = PConstants.RGB;
  public static final int ARGB  = PConstants.ARGB;
  public static final int HSB   = PConstants.HSB;
  public static final int ALPHA = PConstants.ALPHA;

  // image file types
  public static final int TIFF  = PConstants.TIFF;
  public static final int TARGA = PConstants.TARGA;
  public static final int JPEG  = PConstants.JPEG;
  public static final int GIF   = PConstants.GIF;

  // filter/convert types
  public static final int BLUR      = PConstants.BLUR;
  public static final int GRAY      = PConstants.GRAY;
  public static final int INVERT    = PConstants.INVERT;
  public static final int OPAQUE    = PConstants.OPAQUE;
  public static final int POSTERIZE = PConstants.POSTERIZE;
  public static final int THRESHOLD = PConstants.THRESHOLD;
  public static final int ERODE     = PConstants.ERODE;
  public static final int DILATE    = PConstants.DILATE;

  // blend mode keyword definitions
  public static final int REPLACE    = PConstants.REPLACE;
  public static final int BLEND      = PConstants.BLEND;
  public static final int ADD        = PConstants.ADD;
  public static final int SUBTRACT   = PConstants.SUBTRACT;
  public static final int LIGHTEST   = PConstants.LIGHTEST;
  public static final int DARKEST    = PConstants.DARKEST;
  public static final int DIFFERENCE = PConstants.DIFFERENCE;
  public static final int EXCLUSION  = PConstants.EXCLUSION;
  public static final int MULTIPLY   = PConstants.MULTIPLY;
  public static final int SCREEN     = PConstants.SCREEN;
  public static final int OVERLAY    = PConstants.OVERLAY;
  public static final int HARD_LIGHT = PConstants.HARD_LIGHT;
  public static final int SOFT_LIGHT = PConstants.SOFT_LIGHT;
  public static final int DODGE      = PConstants.DODGE;
  public static final int BURN       = PConstants.BURN;

  // for messages
  public static final int CHATTER   = PConstants.CHATTER;
  public static final int COMPLAINT = PConstants.COMPLAINT;
  public static final int PROBLEM   = PConstants.PROBLEM;

  // types of transformation matrices
  public static final int PROJECTION = PConstants.PROJECTION;
  public static final int MODELVIEW  = PConstants.MODELVIEW;

  // types of projection matrices
  public static final int CUSTOM       = PConstants.CUSTOM;
  public static final int ORTHOGRAPHIC = PConstants.ORTHOGRAPHIC;
  public static final int PERSPECTIVE  = PConstants.PERSPECTIVE;

  // shapes
  public static final int GROUP           = PConstants.GROUP;

  public static final int POINT           = PConstants.POINT;
  public static final int POINTS          = PConstants.POINTS;

  public static final int LINE            = PConstants.LINE;
  public static final int LINES           = PConstants.LINES;
  public static final int LINE_STRIP      = PConstants.LINE_STRIP;
  public static final int LINE_LOOP       = PConstants.LINE_LOOP;

  public static final int TRIANGLE        = PConstants.TRIANGLE;
  public static final int TRIANGLES       = PConstants.TRIANGLES;
  public static final int TRIANGLE_STRIP  = PConstants.TRIANGLE_STRIP;
  public static final int TRIANGLE_FAN    = PConstants.TRIANGLE_FAN;

  public static final int QUAD            = PConstants.QUAD;
  public static final int QUADS           = PConstants.QUADS;
  public static final int QUAD_STRIP      = PConstants.QUAD_STRIP;

  public static final int POLYGON         = PConstants.POLYGON;
  public static final int PATH            = PConstants.PATH;

  public static final int RECT            = PConstants.RECT;
  public static final int ELLIPSE         = PConstants.ELLIPSE;
  public static final int ARC             = PConstants.ARC;

  public static final int SPHERE          = PConstants.SPHERE;
  public static final int BOX             = PConstants.BOX;

  // shape closing modes
  public static final int OPEN = PConstants.OPEN;
  public static final int CLOSE = PConstants.CLOSE;

  // shape drawing modes
  public static final int CORNER   = PConstants.CORNER;
  public static final int CORNERS  = PConstants.CORNERS;
  public static final int RADIUS   = PConstants.RADIUS;
  public static final int CENTER   = PConstants.CENTER;
  public static final int DIAMETER = PConstants.DIAMETER;

  // arc drawing modes
  public static final int CHORD  = PConstants.CHORD;
  public static final int PIE    = PConstants.PIE;

  // vertically alignment modes for text
  public static final int BASELINE = PConstants.BASELINE;
  public static final int TOP = PConstants.TOP;
  public static final int BOTTOM = PConstants.BOTTOM;

  // uv texture orientation modes
  public static final int NORMAL     = PConstants.NORMAL;
  public static final int IMAGE      = PConstants.IMAGE;

  // texture wrapping modes
  public static final int CLAMP = PConstants.CLAMP;
  public static final int REPEAT = PConstants.REPEAT;

  // text placement modes
  public static final int MODEL = PConstants.MODEL;
  public static final int SHAPE = PConstants.SHAPE;

  // stroke modes
  public static final int SQUARE   = PConstants.SQUARE;
  public static final int ROUND    = PConstants.ROUND;
  public static final int PROJECT  = PConstants.PROJECT;
  public static final int MITER    = PConstants.MITER;
  public static final int BEVEL    = PConstants.BEVEL;

  // lighting
  public static final int AMBIENT = PConstants.AMBIENT;
  public static final int DIRECTIONAL  = PConstants.DIRECTIONAL;
  public static final int SPOT = PConstants.SPOT;

  // key constants
  public static final char BACKSPACE = PConstants.BACKSPACE;
  public static final char TAB       = PConstants.TAB;
  public static final char ENTER     = PConstants.ENTER;
  public static final char RETURN    = PConstants.RETURN;
  public static final char ESC       = PConstants.ESC;
  public static final char DELETE    = PConstants.DELETE;
  public static final int CODED     = PConstants.CODED;

  public static final int UP        = PConstants.UP;
  public static final int DOWN      = PConstants.DOWN;
  public static final int LEFT      = PConstants.LEFT;
  public static final int RIGHT     = PConstants.RIGHT;

  public static final int ALT       = PConstants.ALT;
  public static final int CONTROL   = PConstants.CONTROL;
  public static final int SHIFT     = PConstants.SHIFT;

  // orientations (only used on Android, ignored on desktop)
  public static final int PORTRAIT = PConstants.PORTRAIT;
  public static final int LANDSCAPE = PConstants.LANDSCAPE;
  public static final int SPAN = PConstants.SPAN;

  // cursor types
  public static final int ARROW = PConstants.ARROW;
  public static final int CROSS = PConstants.CROSS;
  public static final int HAND  = PConstants.HAND;
  public static final int MOVE  = PConstants.MOVE;
  public static final int TEXT  = PConstants.TEXT;
  public static final int WAIT  = PConstants.WAIT;

  // hints
  public static final int DISABLE_DEPTH_TEST         = PConstants.DISABLE_DEPTH_TEST;
  public static final int ENABLE_DEPTH_TEST          = PConstants.ENABLE_DEPTH_TEST;
  public static final int ENABLE_DEPTH_SORT          = PConstants.ENABLE_DEPTH_SORT;
  public static final int DISABLE_DEPTH_SORT         = PConstants.DISABLE_DEPTH_SORT;
  public static final int DISABLE_OPENGL_ERRORS      = PConstants.DISABLE_OPENGL_ERRORS;
  public static final int ENABLE_OPENGL_ERRORS       = PConstants.ENABLE_OPENGL_ERRORS;
  public static final int DISABLE_DEPTH_MASK         = PConstants.DISABLE_DEPTH_MASK;
  public static final int ENABLE_DEPTH_MASK          = PConstants.ENABLE_DEPTH_MASK;
  public static final int DISABLE_OPTIMIZED_STROKE   = PConstants.DISABLE_OPTIMIZED_STROKE;
  public static final int ENABLE_OPTIMIZED_STROKE    = PConstants.ENABLE_OPTIMIZED_STROKE;
  public static final int ENABLE_STROKE_PERSPECTIVE  = PConstants.ENABLE_STROKE_PERSPECTIVE;
  public static final int DISABLE_STROKE_PERSPECTIVE = PConstants.DISABLE_STROKE_PERSPECTIVE;
  public static final int DISABLE_TEXTURE_MIPMAPS    = PConstants.DISABLE_TEXTURE_MIPMAPS;
  public static final int ENABLE_TEXTURE_MIPMAPS     = PConstants.ENABLE_TEXTURE_MIPMAPS;
  public static final int ENABLE_STROKE_PURE         = PConstants.ENABLE_STROKE_PURE;
  public static final int DISABLE_STROKE_PURE        = PConstants.DISABLE_STROKE_PURE;
  public static final int ENABLE_BUFFER_READING      = PConstants.ENABLE_BUFFER_READING;
  public static final int DISABLE_BUFFER_READING     = PConstants.DISABLE_BUFFER_READING;
  public static final int DISABLE_KEY_REPEAT         = PConstants.DISABLE_KEY_REPEAT;
  public static final int ENABLE_KEY_REPEAT          = PConstants.ENABLE_KEY_REPEAT;
  public static final int DISABLE_ASYNC_SAVEFRAME    = PConstants.DISABLE_ASYNC_SAVEFRAME;
  public static final int ENABLE_ASYNC_SAVEFRAME     = PConstants.ENABLE_ASYNC_SAVEFRAME;
  public static final int HINT_COUNT                 = PConstants.HINT_COUNT;

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
  // RANDOM NUMBERS
  public final float random(float high) { return Utils.random(high); }
  public final float randomGaussian() { return Utils.randomGaussian(); }
  public final float random(float low, float high) { return Utils.random(low, high); }
  public final void randomSeed(long seed) { Utils.randomSeed(seed); }

  //////////////////////////////////////////////////////////////
  // PERLIN NOISE
  public final float noise(float x) { return Utils.noise(x); }
  public final float noise(float x, float y) { return Utils.noise(x, y); }
  public final float noise(float x, float y, float z) { return Utils.noise(x, y, z); }
  public final void noiseDetail(int lod) { Utils.noiseDetail(lod); }
  public final void noiseDetail(int lod, float falloff) { Utils.noiseDetail(lod, falloff); }
  public final void noiseSeed(long seed) { Utils.noiseSeed(seed); }

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
