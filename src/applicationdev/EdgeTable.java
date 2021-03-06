import java.util.*;

 class EdgeTable {
/*********************************************************************/
/********* ATTRIBUTES ************************************************/
   private int numFigure;
   private String name;
   private ArrayList alRelatedTables = new ArrayList(), alNativeFields = new ArrayList();
   private int[] relatedTables, relatedFields, nativeFields;
/*********************************************************************/
/********* CONSTRUCTOR ***********************************************/ 
   /**
   * Takes in a inputString and sets the appropriate values 
   * @param inputString String
   */
   public EdgeTable(String inputString) {
      StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
      numFigure = Integer.parseInt(st.nextToken());
      name = st.nextToken();
   }//end EdgeTable
/*********************************************************************/
/********* ACCESSORS *************************************************/       
   /**
   * Gets the num figure
   * @return this.numFigure int */
   public int getNumFigure() { return this.numFigure; }//end getNumFigure
   /**
   * Gets the name
   * @return this.name String */
   public String getName() { return name; }//end getName
   /**
   * Gets all of the related tables from the array list
   * @return this.relatedTables int[] */
   public int[] getRelatedTablesArray() { return this.relatedTables; }//end getRelatedTablesArray
   /**
   * Gets all the related fields from the array list
   * @return this.relatedFields int[] */
   public int[] getRelatedFieldsArray() { return this.relatedFields; }//end getRelatedFieldsArray
   /**
   * Gets the native fields from the array list
   * @return this.nativeFields int[] */
   public int[] getNativeFieldsArray() { return this.nativeFields; }//end getNativeFieldsArray

/**********************************************************************/
/********* MUTATORS ***************************************************/
   /**
   * Sets the related fields
   * @param index int
   * @param relatedValue int */
   public void setRelatedField(int index, int relatedValue) { this.relatedFields[index] = relatedValue; }//end setRelatedField
/*********************************************************************/
/********* METHODS ***************************************************/  
   /**
   * Adds the related table to the array list of relatedTables
   * @param relatedTable int
   */
   public void addRelatedTable(int relatedTable) { this.alRelatedTables.add(new Integer(relatedTable));  }//end addRelatedTable
   
   /**
   * Adds the native field 
   * @param value int
   */
   public void addNativeField(int value) { this.alNativeFields.add(new Integer(value)); }//end addNativeField
	
   /**
   * Moves the field closer to the beginning of the list
   * @param index int
   */
   public void moveFieldUp(int index) {
      final boolean isIndexZero = index == 0;
      if (isIndexZero) {
         return;
      }
      int tempNative = this.nativeFields[index - 1]; //save element at destination index
      this.nativeFields[index - 1] = this.nativeFields[index]; //copy target element to destination
      this.nativeFields[index] = tempNative; //copy saved element to target's original location
      int tempRelated = this.relatedFields[index - 1]; //save element at destination index
      this.relatedFields[index - 1] = this.relatedFields[index]; //copy target element to destination
      this.relatedFields[index] = tempRelated; //copy saved element to target's original location
   }//end moveFieldUp
   
   /**
   * Move the field closer to the end of the list
   * @param index int
   */
   public void moveFieldDown(int index) {
      final boolean isEqualNF = index == (this.nativeFields.length - 1);
      if (isEqualNF) {
         return;
      }
      int tempNative = this.nativeFields[index + 1]; //save element at destination index
      this.nativeFields[index + 1] = nativeFields[index]; //copy target element to destination
      this.nativeFields[index] = tempNative; //copy saved element to target's original location
      int tempRelated = this.relatedFields[index + 1]; //save element at destination index
      this.relatedFields[index + 1] = this.relatedFields[index]; //copy target element to destination
      this.relatedFields[index] = tempRelated; //copy saved element to target's original location
   }//end moveFieldDown
	
   /**
   * Convert the ArrayLists into int[]
   */
   public void makeArrays() { 
      Integer[] temp;
      temp = (Integer[])this.alNativeFields.toArray(new Integer[this.alNativeFields.size()]);
      this.nativeFields = new int[temp.length];
      for (int i = 0; i < temp.length; i++) {
         this.nativeFields[i] = temp[i].intValue();
      }
      
      temp = (Integer[])this.alRelatedTables.toArray(new Integer[this.alRelatedTables.size()]);
      this.relatedTables = new int[temp.length];
      for (int i = 0; i < temp.length; i++) {
         this.relatedTables[i] = temp[i].intValue();
      }
      
      this.relatedFields = new int[this.nativeFields.length];
      for (int i = 0; i < this.relatedFields.length; i++) {
         this.relatedFields[i] = 0;
      }
   }//end makeArrays

   /**
   * Converts the attributes in the class into a string
   * @return String
   */
   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("Table: " + numFigure + "\r\n");
      sb.append("{\r\n");
      sb.append("TableName: " + name + "\r\n");
      sb.append("NativeFields: ");
      for (int i = 0; i < this.nativeFields.length; i++) {
         final boolean isBelowNF = i < (this.nativeFields.length - 1);
         sb.append(this.nativeFields[i]);
         if (isBelowNF){
            sb.append(EdgeConvertFileParser.DELIM);
         }
      }
      sb.append("\r\nRelatedTables: ");
      for (int i = 0; i < this.relatedTables.length; i++) {
         final boolean isBelowRT = i < (this.relatedTables.length - 1);
         sb.append(this.relatedTables[i]);
         if (isBelowRT){
            sb.append(EdgeConvertFileParser.DELIM);
         }
      }
      sb.append("\r\nRelatedFields: ");
      for (int i = 0; i < this.relatedFields.length; i++) {
         final boolean isBelowRF = i < (this.relatedFields.length - 1);
         sb.append(this.relatedFields[i]);
         if (isBelowRF){
            sb.append(EdgeConvertFileParser.DELIM);
         }
      }
      sb.append("\r\n}\r\n");
      
      return sb.toString();
   }//end toString
}//end EdgeTable
