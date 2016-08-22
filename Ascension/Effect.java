import processing.core.PApplet;
import processing.core.PConstants;

import heronarts.lx.LX;
import heronarts.lx.effect.LXEffect;

abstract class Effect extends LXEffect {

  protected final Model model;

  public Effect(LX lx) {
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
  static public final int second() { return PApplet.second(); }
  static public final int minute() { return PApplet.minute(); }
  static public final int hour() { return PApplet.hour(); }
  static public final int day() { return PApplet.day(); }
  static public final int month() { return PApplet.month(); }
  static public final int year() { return PApplet.year(); }

  //////////////////////////////////////////////////////////////
  // printing
  public static final void print(byte what) { PApplet.print(what); }
  public static final void print(boolean what) { PApplet.print(what); }
  public static final void print(char what) { PApplet.print(what); }
  public static final void print(int what) { PApplet.print(what); }
  public static final void print(long what) { PApplet.print(what); }
  public static final void print(float what) { PApplet.print(what); }
  public static final void print(double what) { PApplet.print(what); }
  public static final void print(String what) { PApplet.print(what); }
  public static final void print(Object... variables) { PApplet.print(variables); }
  public static final void println() { PApplet.println(); }
  public static final void println(byte what) { PApplet.println(what); }
  public static final void println(boolean what) { PApplet.println(what); }
  public static final void println(char what) { PApplet.println(what); }
  public static final void println(int what) { PApplet.println(what); }
  public static final void println(long what) { PApplet.println(what); }
  public static final void println(float what) { PApplet.println(what); }
  public static final void println(double what) { PApplet.println(what); }
  public static final void println(String what) { PApplet.println(what); }
  public static final void println(Object... variables) { PApplet.println(variables); }
  public static final void println(Object what) { PApplet.println(what); }
  public static final void printArray(Object what) { PApplet.println(what); }
  public static final void debug(String msg) { PApplet.debug(msg); }

  //////////////////////////////////////////////////////////////
  // MATH
  public static final float abs(float n) { return PApplet.abs(n); }
  public static final int abs(int n) { return PApplet.abs(n); }
  public static final float sq(float n) { return PApplet.sq(n); }
  public static final float sqrt(float n) { return PApplet.sqrt(n); }
  public static final float log(float n) { return PApplet.log(n); }
  public static final float exp(float n) { return PApplet.exp(n); }
  public static final float pow(float n, float e) { return PApplet.pow(n, e); }
  public static final int max(int a, int b) { return PApplet.max(a, b); }
  public static final float max(float a, float b) { return PApplet.max(a, b); }
  public static final int max(int a, int b, int c) { return PApplet.max(a, b, c); }
  public static final float max(float a, float b, float c) { return PApplet.max(a, b, c); }
  public static final int max(int[] list) { return PApplet.max(list); }
  public static final float max(float[] list) { return PApplet.max(list); }
  public static final int min(int a, int b) { return PApplet.min(a, b); }
  public static final float min(float a, float b) { return PApplet.min(a, b); }
  public static final int min(int a, int b, int c) { return PApplet.min(a, b, c); }
  public static final float min(float a, float b, float c) { return PApplet.min(a, b, c); }
  public static final int min(int[] list) { return PApplet.min(list); }
  public static final float min(float[] list) { return PApplet.min(list); }
  public static final int constrain(int amt, int low, int high) { return PApplet.constrain(amt, low, high); }
  public static final float constrain(float amt, float low, float high) { return PApplet.constrain(amt, low, high); }
  public static final float sin(float angle) { return PApplet.sin(angle); }
  public static final float cos(float angle) { return PApplet.cos(angle); }
  public static final float tan(float angle) { return PApplet.tan(angle); }
  public static final float asin(float value) { return PApplet.asin(value); }
  public static final float acos(float value) { return PApplet.acos(value); }
  public static final float atan(float value) { return PApplet.atan(value); }
  public static final float atan2(float y, float x) { return PApplet.atan2(y, x); }
  public static final float degrees(float radians) { return PApplet.degrees(radians); }
  public static final float radians(float degrees) { return PApplet.radians(degrees); }
  public static final int ceil(float n) { return PApplet.ceil(n); }
  public static final int floor(float n) { return PApplet.floor(n); }
  public static final int round(float n) { return PApplet.round(n); }
  public static final float mag(float a, float b) { return PApplet.mag(a, b); }
  public static final float mag(float a, float b, float c) { return PApplet.mag(a, b, c); }
  public static final float dist(float x1, float y1, float x2, float y2) { return PApplet.dist(x1, y1, x2, y2); }
  public static final float dist(float x1, float y1, float z1,
                                 float x2, float y2, float z2) { return PApplet.dist(x1, y1, z1, x2, y2, z2); }
  public static final float lerp(float start, float stop, float amt) { return PApplet.lerp(start, stop, amt); }
  public static final float norm(float value, float start, float stop) { return PApplet.norm(value, start, stop); }
  public static final float map(float value,
                                float start1, float stop1,
                                float start2, float stop2) { return PApplet.map(value, start1, stop1, start2, stop2); }


  //////////////////////////////////////////////////////////////
  // RANDOM NUMBERS
  public static final float random(float high) { return Utils.random(high); }
  public static final float randomGaussian() { return Utils.randomGaussian(); }
  public static final float random(float low, float high) { return Utils.random(low, high); }
  public static final void randomSeed(long seed) { Utils.randomSeed(seed); }

  //////////////////////////////////////////////////////////////
  // PERLIN NOISE
  public static final float noise(float x) { return Utils.noise(x); }
  public static final float noise(float x, float y) { return Utils.noise(x, y); }
  public static final float noise(float x, float y, float z) { return Utils.noise(x, y, z); }
  public static final void noiseDetail(int lod) { Utils.noiseDetail(lod); }
  public static final void noiseDetail(int lod, float falloff) { Utils.noiseDetail(lod, falloff); }
  public static final void noiseSeed(long seed) { Utils.noiseSeed(seed); }

  //////////////////////////////////////////////////////////////
  // SORT
  public static final byte[] sort(byte list[]) { return PApplet.sort(list); }
  public static final byte[] sort(byte[] list, int count) { return PApplet.sort(list, count); }
  public static final char[] sort(char list[]) { return PApplet.sort(list); }
  public static final char[] sort(char[] list, int count) { return PApplet.sort(list, count); }
  public static final int[] sort(int list[]) { return PApplet.sort(list); }
  public static final int[] sort(int[] list, int count) { return PApplet.sort(list, count); }
  public static final float[] sort(float list[]) { return PApplet.sort(list); }
  public static final float[] sort(float[] list, int count) { return PApplet.sort(list, count); }
  public static final String[] sort(String list[]) { return PApplet.sort(list); }
  public static final String[] sort(String[] list, int count) { return PApplet.sort(list, count); }

  //////////////////////////////////////////////////////////////
  // ARRAY UTILITIES
  public static final void arrayCopy(Object src, int srcPosition,
                               Object dst, int dstPosition,
                               int length) { PApplet.arrayCopy(src, srcPosition, dst, dstPosition, length); }
  public static final void arrayCopy(Object src, Object dst, int length) { PApplet.arrayCopy(src, dst, length); }
  public static final void arrayCopy(Object src, Object dst) { PApplet.arrayCopy(src, dst); }
  public static final boolean[] expand(boolean list[]) { return PApplet.expand(list); }
  public static final boolean[] expand(boolean list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final byte[] expand(byte list[]) { return PApplet.expand(list); }
  public static final byte[] expand(byte list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final char[] expand(char list[]) { return PApplet.expand(list); }
  public static final char[] expand(char list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final int[] expand(int list[]) { return PApplet.expand(list); }
  public static final int[] expand(int list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final long[] expand(long list[]) { return PApplet.expand(list); }
  public static final long[] expand(long list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final float[] expand(float list[]) { return PApplet.expand(list); }
  public static final float[] expand(float list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final double[] expand(double list[]) { return PApplet.expand(list); }
  public static final double[] expand(double list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final String[] expand(String list[]) { return PApplet.expand(list); }
  public static final String[] expand(String list[], int newSize) { return PApplet.expand(list, newSize); }
  public static final Object expand(Object array) { return PApplet.expand(array); }
  public static final Object expand(Object list, int newSize) { return PApplet.expand(list, newSize); }
  public static final byte[] append(byte array[], byte value) { return PApplet.append(array, value); }
  public static final char[] append(char array[], char value) { return PApplet.append(array, value); }
  public static final int[] append(int array[], int value) { return PApplet.append(array, value); }
  public static final float[] append(float array[], float value) { return PApplet.append(array, value); }
  public static final String[] append(String array[], String value) { return PApplet.append(array, value); }
  public static final Object append(Object array, Object value) { return PApplet.append(array, value); }
  public static final boolean[] shorten(boolean list[]) { return PApplet.shorten(list); }
  public static final byte[] shorten(byte list[]) { return PApplet.shorten(list); }
  public static final char[] shorten(char list[]) { return PApplet.shorten(list); }
  public static final int[] shorten(int list[]) { return PApplet.shorten(list); }
  public static final float[] shorten(float list[]) { return PApplet.shorten(list); }
  public static final String[] shorten(String list[]) { return PApplet.shorten(list); }
  public static final Object shorten(Object list) { return PApplet.shorten(list); }
  public static final boolean[] splice(boolean list[],
                                       boolean value, int index) { return PApplet.splice(list, value, index); }
  public static final boolean[] splice(boolean list[],
                                       boolean value[], int index) { return PApplet.splice(list, value, index); }
  public static final byte[] splice(byte list[],
                                    byte value, int index) { return PApplet.splice(list, value, index); }
  public static final byte[] splice(byte list[],
                                    byte value[], int index) { return PApplet.splice(list, value, index); }
  public static final char[] splice(char list[],
                                    char value, int index) { return PApplet.splice(list, value, index); }
  public static final char[] splice(char list[],
                                    char value[], int index) { return PApplet.splice(list, value, index); }
  public static final int[] splice(int list[],
                                   int value, int index) { return PApplet.splice(list, value, index); }
  public static final int[] splice(int list[],
                                   int value[], int index) { return PApplet.splice(list, value, index); }
  public static final float[] splice(float list[],
                                     float value, int index) { return PApplet.splice(list, value, index); }
  public static final float[] splice(float list[],
                                     float value[], int index) { return PApplet.splice(list, value, index); }
  public static final String[] splice(String list[],
                                      String value, int index) { return PApplet.splice(list, value, index); }
  public static final String[] splice(String list[],
                                      String value[], int index) { return PApplet.splice(list, value, index); }
  public static final Object splice(Object list, Object value, int index) { return PApplet.splice(list, value, index); }
  public static final boolean[] subset(boolean list[], int start) { return PApplet.subset(list, start); }
  public static final boolean[] subset(boolean list[], int start, int count) { return PApplet.subset(list, start, count); }
  public static final byte[] subset(byte list[], int start) { return PApplet.subset(list, start); }
  public static final byte[] subset(byte list[], int start, int count) { return PApplet.subset(list, start, count); }
  public static final char[] subset(char list[], int start) { return PApplet.subset(list, start); }
  public static final char[] subset(char list[], int start, int count) { return PApplet.subset(list, start, count); }
  public static final int[] subset(int list[], int start) { return PApplet.subset(list, start); }
  public static final int[] subset(int list[], int start, int count) { return PApplet.subset(list, start, count); }
  public static final float[] subset(float list[], int start) { return PApplet.subset(list, start); }
  public static final float[] subset(float list[], int start, int count) { return PApplet.subset(list, start, count); }
  public static final String[] subset(String list[], int start) { return PApplet.subset(list, start); }
  public static final String[] subset(String list[], int start, int count) { return PApplet.subset(list, start, count); }
  public static final Object subset(Object list, int start) { return PApplet.subset(list, start); }
  public static final Object subset(Object list, int start, int count) { return PApplet.subset(list, start, count); }
  public static final boolean[] concat(boolean a[], boolean b[]) { return PApplet.concat(a, b); }
  public static final byte[] concat(byte a[], byte b[]) { return PApplet.concat(a, b); }
  public static final char[] concat(char a[], char b[]) { return PApplet.concat(a, b); }
  public static final int[] concat(int a[], int b[]) { return PApplet.concat(a, b); }
  public static final float[] concat(float a[], float b[]) { return PApplet.concat(a, b); }
  public static final String[] concat(String a[], String b[]) { return PApplet.concat(a, b); }
  public static final Object concat(Object a, Object b) { return PApplet.concat(a, b); }
  public static final boolean[] reverse(boolean list[]) { return PApplet.reverse(list); }
  public static final byte[] reverse(byte list[]) { return PApplet.reverse(list); }
  public static final char[] reverse(char list[]) { return PApplet.reverse(list); }
  public static final int[] reverse(int list[]) { return PApplet.reverse(list); }
  public static final float[] reverse(float list[]) { return PApplet.reverse(list); }
  public static final String[] reverse(String list[]) { return PApplet.reverse(list); }
  public static final Object reverse(Object list) { return PApplet.reverse(list); }

  //////////////////////////////////////////////////////////////
  // STRINGS
  public static final String trim(String str) { return PApplet.trim(str); }
  public static final String[] trim(String[] array) { return PApplet.trim(array); }
  public static final String join(String[] list, char separator) { return PApplet.join(list, separator); }
  public static final String join(String[] list, String separator) { return PApplet.join(list, separator); }
  public static final String[] splitTokens(String value) { return PApplet.splitTokens(value); }
  public static final String[] splitTokens(String value, String delim) { return PApplet.splitTokens(value, delim); }
  public static final String[] split(String value, char delim) { return PApplet.split(value, delim); }
  public static final String[] split(String value, String delim) { return PApplet.split(value, delim); }
  public static final String[] match(String str, String regexp) { return PApplet.match(str, regexp); }
  public static final String[][] matchAll(String str, String regexp) { return PApplet.matchAll(str, regexp); }

  //////////////////////////////////////////////////////////////
  // CASTING FUNCTIONS
  public static final boolean parseBoolean(int what) { return PApplet.parseBoolean(what); }
  public static final boolean parseBoolean(String what) { return PApplet.parseBoolean(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final boolean[] parseBoolean(int what[]) { return PApplet.parseBoolean(what); }
  public static final boolean[] parseBoolean(String what[]) { return PApplet.parseBoolean(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final byte parseByte(boolean what) { return PApplet.parseByte(what); }
  public static final byte parseByte(char what) { return PApplet.parseByte(what); }
  public static final byte parseByte(int what) { return PApplet.parseByte(what); }
  public static final byte parseByte(float what) { return PApplet.parseByte(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final byte[] parseByte(boolean what[]) { return PApplet.parseByte(what); }
  public static final byte[] parseByte(char what[]) { return PApplet.parseByte(what); }
  public static final byte[] parseByte(int what[]) { return PApplet.parseByte(what); }
  public static final byte[] parseByte(float what[]) { return PApplet.parseByte(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final char parseChar(byte what) { return PApplet.parseChar(what); }
  public static final char parseChar(int what) { return PApplet.parseChar(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final char[] parseChar(byte what[]) { return PApplet.parseChar(what); }
  public static final char[] parseChar(int what[]) { return PApplet.parseChar(what); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final int parseInt(boolean what) { return PApplet.parseInt(what); }
  public static final int parseInt(byte what) { return PApplet.parseInt(what); }
  public static final int parseInt(char what) { return PApplet.parseInt(what); }
  public static final int parseInt(float what) { return PApplet.parseInt(what); }
  public static final int parseInt(String what) { return PApplet.parseInt(what); }
  public static final int parseInt(String what, int otherwise) { return PApplet.parseInt(what, otherwise); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final int[] parseInt(boolean what[]) { return PApplet.parseInt(what); }
  public static final int[] parseInt(byte what[]) { return PApplet.parseInt(what); }
  public static final int[] parseInt(char what[]) { return PApplet.parseInt(what); }
  public static final int[] parseInt(float what[]) { return PApplet.parseInt(what); }
  public static final int[] parseInt(String what[]) { return PApplet.parseInt(what); }
  public static final int[] parseInt(String what[], int missing) { return PApplet.parseInt(what, missing); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final float parseFloat(int what) { return PApplet.parseFloat(what); }
  public static final float parseFloat(String what) { return PApplet.parseFloat(what); }
  public static final float parseFloat(String what, float otherwise) { return PApplet.parseFloat(what, otherwise); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final float[] parseFloat(byte what[]) { return PApplet.parseFloat(what); }
  public static final float[] parseFloat(int what[]) { return PApplet.parseFloat(what); }
  public static final float[] parseFloat(String what[]) { return PApplet.parseFloat(what); }
  public static final float[] parseFloat(String what[], float missing) { return PApplet.parseFloat(what, missing); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final String str(boolean x) { return PApplet.str(x); }
  public static final String str(byte x) { return PApplet.str(x); }
  public static final String str(char x) { return PApplet.str(x); }
  public static final String str(int x) { return PApplet.str(x); }
  public static final String str(float x) { return PApplet.str(x); }
  // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  public static final String[] str(boolean x[]) { return PApplet.str(x); }
  public static final String[] str(byte x[]) { return PApplet.str(x); }
  public static final String[] str(char x[]) { return PApplet.str(x); }
  public static final String[] str(float x[]) { return PApplet.str(x); }

  //////////////////////////////////////////////////////////////
  // INT NUMBER FORMATTING
  public static final String nf(float num) { return PApplet.nf(num); }
  public static final String[] nf(float[] num) { return PApplet.nf(num); }
  public static final String[] nf(int num[], int digits) { return PApplet.nf(num, digits); }
  public static final String nf(int num, int digits) { return PApplet.nf(num, digits); }
  public static final String[] nfc(int num[]) { return PApplet.nfc(num); }
  public static final String nfc(int num) { return PApplet.nfc(num); }
  public static final String nfs(int num, int digits) { return PApplet.nfs(num, digits); }
  public static final String[] nfs(int num[], int digits) { return PApplet.nfs(num, digits); }
  public static final String nfp(int num, int digits) { return PApplet.nfp(num, digits); }
  public static final String[] nfp(int num[], int digits) { return PApplet.nfp(num, digits); }

  //////////////////////////////////////////////////////////////
  // FLOAT NUMBER FORMATTING
  public static final String[] nf(float num[], int left, int right) { return PApplet.nf(num, left, right); }
  public static final String nf(float num, int left, int right) { return PApplet.nf(num, left, right); }
  public static final String[] nfc(float num[], int right) { return PApplet.nfc(num, right); }
  public static final String nfc(float num, int right) { return PApplet.nfc(num, right); }
  public static final String[] nfs(float num[], int left, int right) { return PApplet.nfs(num, left, right); }
  public static final String nfs(float num, int left, int right) { return PApplet.nfs(num, left, right); }
  public static final String[] nfp(float num[], int left, int right) { return PApplet.nfp(num, left, right); }
  public static final String nfp(float num, int left, int right) { return PApplet.nfp(num, left, right); }

  //////////////////////////////////////////////////////////////
  // HEX/BINARY CONVERSION
  public static final String hex(byte value) { return PApplet.hex(value); }
  public static final String hex(char value) { return PApplet.hex(value); }
  public static final String hex(int value) { return PApplet.hex(value); }
  public static final String hex(int value, int digits) { return PApplet.hex(value, digits); }
  public static final int unhex(String value) { return PApplet.unhex(value); }
  public static final String binary(byte value) { return PApplet.binary(value); }
  public static final String binary(char value) { return PApplet.binary(value); }
  public static final String binary(int value) { return PApplet.binary(value); }
  public static final String binary(int value, int digits) { return PApplet.binary(value, digits); }
  public static final int unbinary(String value) { return PApplet.unbinary(value); }

  //////////////////////////////////////////////////////////////
  // COLOR FUNCTIONS
  public static final int blendColor(int c1, int c2, int mode) { return PApplet.blendColor(c1, c2, mode); }
  public static final int lerpColor(int c1, int c2, float amt, int mode) { return PApplet.lerpColor(c1, c2, amt, mode); }

}
