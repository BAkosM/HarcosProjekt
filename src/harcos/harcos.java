package harcos;

public class Harcos{
    private String nev;
    private int szint;
    private int tapasztalat;
    private int eletero;
    private int alapEletero;
    private int alapSebzes;

    public Harcos(String nev, String statuszSablon) {
        this.nev = nev;
        this.szint = 1;
        this.tapasztalat = 0;
        //warrior
        if(statuszSablon.equals("1")){
            this.alapEletero = 15;
            this.alapSebzes = 3;
        //ranger
        }else if(statuszSablon.equals("2")){
            this.alapEletero = 12;
            this.alapSebzes = 4;
        //mage
        }else if(statuszSablon.equals("3")){
            this.alapEletero = 8;
            this.alapSebzes = 5;
        }else{
            System.out.println("Hibás Státusz Sablon!");
        }
        this.eletero = getMaxEletero();
    }
    public String getNev(){
        return nev;
    }
    public void setNev(String nev) {
        this.nev = nev;
    }
    public int getSzint(){
        return szint;
    }
    public void setSzint(int szint) {
        this.szint = szint;
    }
    public int getTapasztalat(){
        return tapasztalat;
    }
    public void setTapasztalat(int tapasztalat){
        this.tapasztalat = tapasztalat;
        if (this.tapasztalat >= this.getSzintLepeshez()){
            this.setSzint(this.getSzint()+1);
            this.setTapasztalat(this.getTapasztalat()-this.getSzintLepeshez());
            this.setEletero(this.getMaxEletero());
        }
    }
    public int getAlapEletero(){
        return alapEletero;
    }
    public int getAlapSebzes(){
        return alapSebzes;
    }
    public int getEletero(){
        return eletero;
    }
    public void setEletero(int eletero){
        this.eletero = eletero;
        if(this.eletero > this.getMaxEletero()){
            this.eletero = this.getMaxEletero();
        }
    }
    public int getSebzes(){
        return alapSebzes + szint;
    }
    public int getSzintLepeshez(){
        return 10 + (szint*5);
    }
    public int getMaxEletero(){
        return alapEletero + (szint*3);
    }
    public void megkuzd(Harcos masikHarcos){
        if(masikHarcos.nev.equals(this.nev)){
            System.out.println("Nem tud magával megküzdeni!");
        }else{
            if(masikHarcos.eletero <= 0 || this.eletero <= 0){
                System.out.println("Nem tud hullával megküzdeni!");
            } else {
                //Fight!
                masikHarcos.setEletero(masikHarcos.getEletero()- this.getSebzes());
                if (masikHarcos.eletero > 0){
                    this.setEletero(this.getEletero()- masikHarcos.getSebzes());
                    if(this.eletero > 0){
                        this.setTapasztalat(this.getTapasztalat() + 5);
                        masikHarcos.setTapasztalat(masikHarcos.getTapasztalat() + 5);
                    }else{
                        masikHarcos.setTapasztalat(masikHarcos.getTapasztalat() + 15);
                        this.setTapasztalat(0);
                    }
                } else {
                    this.setTapasztalat(this.getTapasztalat() + 15);
                    masikHarcos.setTapasztalat(0);
                }
            }
        }
    }
    public void gyogyul(){
        if(this.eletero <= 0){
            this.setEletero(this.getMaxEletero());
        } else {
            this.setEletero(this.getEletero() + (3 + szint));
        }
    }
    @Override
    public String toString() {
        return String.format("%s - LVL: %d - EXP: %d/%d - HP: %d/%d - DMG: %d", nev,szint,tapasztalat,getSzintLepeshez(),eletero,getMaxEletero(),getSebzes());
    }
}