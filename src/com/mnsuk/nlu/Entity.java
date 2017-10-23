

/* First created by JCasGen Wed Apr 05 13:29:03 BST 2017 */
package com.mnsuk.nlu;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Oct 23 18:03:32 BST 2017
 * XML source: /Users/martin/Documents/Development/Java/Annotators/NLU/desc/NLU-ae.xml
 * @generated */
public class Entity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Entity.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Entity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Entity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Entity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Entity(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: entityType

  /** getter for entityType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEntityType() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_entityType);}
    
  /** setter for entityType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEntityType(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_entityType, v);}    
   
    
  //*--------------*
  //* Feature: sentiment

  /** getter for sentiment - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSentiment() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_sentiment == null)
      jcasType.jcas.throwFeatMissing("sentiment", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_sentiment);}
    
  /** setter for sentiment - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentiment(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_sentiment == null)
      jcasType.jcas.throwFeatMissing("sentiment", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_sentiment, v);}    
   
    
  //*--------------*
  //* Feature: anger

  /** getter for anger - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnger() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_anger == null)
      jcasType.jcas.throwFeatMissing("anger", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_anger);}
    
  /** setter for anger - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnger(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_anger == null)
      jcasType.jcas.throwFeatMissing("anger", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_anger, v);}    
   
    
  //*--------------*
  //* Feature: disgust

  /** getter for disgust - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDisgust() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_disgust == null)
      jcasType.jcas.throwFeatMissing("disgust", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_disgust);}
    
  /** setter for disgust - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDisgust(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_disgust == null)
      jcasType.jcas.throwFeatMissing("disgust", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_disgust, v);}    
   
    
  //*--------------*
  //* Feature: fear

  /** getter for fear - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFear() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_fear == null)
      jcasType.jcas.throwFeatMissing("fear", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_fear);}
    
  /** setter for fear - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFear(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_fear == null)
      jcasType.jcas.throwFeatMissing("fear", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_fear, v);}    
   
    
  //*--------------*
  //* Feature: joy

  /** getter for joy - gets 
   * @generated
   * @return value of the feature 
   */
  public String getJoy() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_joy == null)
      jcasType.jcas.throwFeatMissing("joy", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_joy);}
    
  /** setter for joy - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setJoy(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_joy == null)
      jcasType.jcas.throwFeatMissing("joy", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_joy, v);}    
   
    
  //*--------------*
  //* Feature: sadness

  /** getter for sadness - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSadness() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_sadness == null)
      jcasType.jcas.throwFeatMissing("sadness", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_sadness);}
    
  /** setter for sadness - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSadness(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_sadness == null)
      jcasType.jcas.throwFeatMissing("sadness", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_sadness, v);}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets The source of the entity either "default" or a custom model id.
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "com.mnsuk.nlu.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets The source of the entity either "default" or a custom model id. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "com.mnsuk.nlu.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_source, v);}    
  }

    