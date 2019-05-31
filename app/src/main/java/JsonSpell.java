import com.google.gson.annotations.SerializedName;

public class JsonSpell {
    @SerializedName("_id")
    private String spellId;

    @SerializedName("index")
    private int spellIndex;

    @SerializedName("name")
    private String spellName;

    @SerializedName("desc")
    private String spelldesc;

    @SerializedName("higher_level")
    private String spellHigherLevel;

    @SerializedName("page")
    private String spellPage;

    @SerializedName("range")
    private String spellrange;

    @SerializedName("components")
    private String[] spellComponents;

    @SerializedName("material")
    private String spellMaterial;

    @SerializedName("ritual")
    private String spellRitual;

    @SerializedName("duration")
    private String spellDuration;

    @SerializedName("concentration")
    private String spellConcentration;

    @SerializedName("casting_time")
    private String spellCastingTime;

    @SerializedName("level")
    private int spellLevel;

    // TODO: create object for schools, classes, subclasses etc. LOT OF WORK, LOW PRIORIT
    @SerializedName("school")
    private String spellSchool;

    // @SerializedName("name")
    // private String[] schoolname;

    @SerializedName("classes")
    private String[] spellClasses;

    @SerializedName("subclasses")
    private String[] spellSubclasses;

    @SerializedName("url")
    private String spellURL;

    // constructor
    public JsonSpell() {

    }

    public String getSpellId() {
        return spellId;
    }

    public void setSpellId(String spellId) {
        this.spellId = spellId;
    }

    public int getSpellIndex() {
        return spellIndex;
    }

    public void setSpellIndex(int spellIndex) {
        this.spellIndex = spellIndex;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getSpelldesc() {
        return spelldesc;
    }

    public void setSpelldesc(String spelldesc) {
        this.spelldesc = spelldesc;
    }

    public String getSpellHigherLevel() {
        return spellHigherLevel;
    }

    public void setSpellHigherLevel(String spellHigherLevel) {
        this.spellHigherLevel = spellHigherLevel;
    }

    public String getSpellPage() {
        return spellPage;
    }

    public void setSpellPage(String spellPage) {
        this.spellPage = spellPage;
    }

    public String getSpellrange() {
        return spellrange;
    }

    public void setSpellrange(String spellrange) {
        this.spellrange = spellrange;
    }

    public String[] getSpellComponents() {
        return spellComponents;
    }

    public void setSpellComponents(String[] spellComponents) {
        this.spellComponents = spellComponents;
    }

    public String getSpellMaterial() {
        return spellMaterial;
    }

    public void setSpellMaterial(String spellMaterial) {
        this.spellMaterial = spellMaterial;
    }

    public String getSpellRitual() {
        return spellRitual;
    }

    public void setSpellRitual(String spellRitual) {
        this.spellRitual = spellRitual;
    }

    public String getSpellDuration() {
        return spellDuration;
    }

    public void setSpellDuration(String spellDuration) {
        this.spellDuration = spellDuration;
    }

    public String getSpellConcentration() {
        return spellConcentration;
    }

    public void setSpellConcentration(String spellConcentration) {
        this.spellConcentration = spellConcentration;
    }

    public String getSpellCastingTime() {
        return spellCastingTime;
    }

    public void setSpellCastingTime(String spellCastingTime) {
        this.spellCastingTime = spellCastingTime;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public String getSpellSchool() {
        return spellSchool;
    }

    public void setSpellSchool(String spellSchool) {
        this.spellSchool = spellSchool;
    }

    public String[] getSpellClasses() {
        return spellClasses;
    }

    public void setSpellClasses(String[] spellClasses) {
        this.spellClasses = spellClasses;
    }

    public String[] getSpellSubclasses() {
        return spellSubclasses;
    }

    public void setSpellSubclasses(String[] spellSubclasses) {
        this.spellSubclasses = spellSubclasses;
    }

    public String getSpellURL() {
        return spellURL;
    }

    public void setSpellURL(String spellURL) {
        this.spellURL = spellURL;
    }
}
/*
JSON returned by an api call
{
  "_id":"5bce91f95b7768e7920184fc",
  "index":41,
  "name":"Circle of Death",
  "desc":["A sphere of negative energy ripples out in a 60-footradius sphere from a point within range. Each creature in that area must make a constitution saving throw. A target takes 8d6 necrotic damage on a failed save, or half as much damage on a successful one."],
  "higher_level":["When you cast this spell using a spell slot of 7th level or higher, the damage increases by 2d6 for each slot level above 6th."],
  "page":"phb 221",
  "range":"150 feet",
  "components":["V","S","M"],
  "material":"The powder of a crushed black pearl worth at least 500 gp.",
  "ritual":"no",
  "duration":"Instantaneous",
  "concentration":"no",
  "casting_time":"1 action",
  "level":6,
  "school":{
    "name":"Necromancy",
      "url":"http://www.dnd5eapi.co/api/magic-schools/7"
  },
  "classes":[
    {"url":"http://www.dnd5eapi.co/api/classes/10","name":"Sorcerer"},
    {"url":"http://www.dnd5eapi.co/api/classes/11", "name":"Warlock"},
    {"url":"http://www.dnd5eapi.co/api/classes/12", "name":"Wizard"}
  ],
  "subclasses":[],
  "url":"http://www.dnd5eapi.co/api/spells/41"
}
 */


