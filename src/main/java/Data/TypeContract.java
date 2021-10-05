/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author admink
 */
public class TypeContract {
    private int TypeID;
    private String NameType;

    public TypeContract() {
    }

    public TypeContract(int TypeID, String NameType) {
        this.TypeID = TypeID;
        this.NameType = NameType;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public String getNameType() {
        return NameType;
    }

    public void setTypeName(String NameType) {
        this.NameType = NameType;
    }
    public Object[] toArray(){
        
        return new Object[]{TypeID,NameType};
        
    }

    public void getNameType(String nameType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
