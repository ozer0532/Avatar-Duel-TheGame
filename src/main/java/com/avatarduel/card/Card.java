abstract class Card {
    protected String Name;
    protected String Element;
    protected String Description;
    protected CardSprite Sprite;

    public Card(String name, String elmt, String desc, CardSprite spr) {
        this.Name=name;
        this.Element=elmt;
        this.Description=desc;
        this.Sprite=spr;
    }

    // SETTER
    public void setName(String name) {
        this.Name=name;
    }
    public void setElmt(String elmt) {
        this.Element=elmt;
    }
    public void setDesc(String desc) {
        this.Description=desc;
    }
    public void setSprite(String spr) {
        this.Sprite=spr;
    }

    // GETTER
    public String getName() {
        return this.Name;
    }
    public String getElmt() {
        return this.Element;
    }
    public String getDesc() {
        return this.Description;
    }
    public String getSprite() {
        return this.Sprite;
    }

    // METHODS
    public abstract void OnCardPlayed(GameManager gm);
    public abstract void DrawCardSimple(float x, float y, boolean isFlipped);
    public abstract void DrawCardDetail();
    public abstract void CanBePlayed(PlayerStats ps);

}