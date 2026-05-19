package com.smk.falou;



import java.text.SimpleDateFormat;
import java.util.Date;
import org.zkforge.ckez.CKeditor;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;


public class MyViewModel {
    
  @Wire//permet de lier les elements du zul aux elements du viewmodel
  Textbox Number;
  @Wire
  Textbox title;
  @Wire
  CKeditor msgbox;
  @Wire
  Combobox cycle;
  @Wire
  Combobox filter1;
  @Wire
  Combobox filter2;
  @Wire
  Button apply; 
 
  //declaration des objets permettant de créer des listes
  ListModelList<Object[]> cycles;
  ListModelList<Object[]> filter1items;
  ListModelList<Object[]> filter2items;  
  ListModelList<Object[]> listitems;
  
  @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
        try {
            Selectors.wireComponents(view, this, false);
            Selectors.wireEventListeners(view, this);
            //creation
            cycles = new ListModelList<>();
            filter1items = new ListModelList<>();
            filter2items = new ListModelList<>();
            listitems = new ListModelList<>();
            cycles.add(new Object[]{"TOUS LES CYCLES", 12L, "ALL"});     
            cycles.add(new Object[]{"CYCLES UNIVERSITAIRES", 12L, "UNI"});
            cycles.add(new Object[]{"CYCLES FORMATION CONTINUE", 12L, "CFC"});
            cycles.add(new Object[]{"CYCLES BTS", 12L, "BTS"});
            listitems.add(new Object[]{"Nouveau"});
        } catch (Exception ex) {
            Messagebox.show("Une erreur est survenue lors du chargement de la page\n" + ex.getMessage(), "Erreur", Messagebox.OK, Messagebox.EXCLAMATION);
        }
    }

    public ListModelList<Object[]> getCycles() { 
        return cycles;
    }
    
    public ListModelList<Object[]> getFilter1items() {
        return filter1items;
    }

    public ListModelList<Object[]> getFilter2items() {
        return filter2items;
    }
    
    public ListModelList<Object[]> getListitems() {
        return listitems;
    }
    
    @Listen("onSelect = #cycle")
    public void onselectCycle(){
        //init
        filter1.setDisabled(false);
        filter2.setDisabled(false);
        filter1items.clear();
        filter2items.clear();
        //
        Object[] selitem = cycles.getSelection().iterator().next();
        switch((String)selitem[2]){
            case "ALL":
               filter1.setDisabled(true);
                filter2.setDisabled(true);
            break;
            case "UNI":
            case "CFC":
                filter2.setDisabled(true);
                filter1items.add(new Object[]{"UFR", "UFR"});     
                filter1items.add(new Object[]{"DEPARTEMENT", "DEP"});
            break;    
            case "BTS":
                filter1.setDisabled(true); 
            break;    
        }
    }
    
    @Listen("onSelect = #filter1")
    public void onselectFiltre1(){
        //init
        filter2items.clear();
        //
         filter2.setDisabled(false);
        Object[] selitem = filter1items.getSelection().iterator().next();//re
        switch((String)selitem[1]){
            case "UFR":
                filter2items.add(new Object[]{"SCIENCES BIOLOGIQUES", 4L, "UFR"});     
                filter2items.add(new Object[]{"SCIENCES SOCIALES", 5L, "UFR"});
            break;
            case "DEP":
                filter2items.add(new Object[]{"MPC", 6L, "DEP"});     
                filter2items.add(new Object[]{"LICENCE 1", 7L, "LEVEL"});
            break;
        }
    }
    @Listen("onClick = #apply")
    public void apply(){
       //verification des champs
       
       //recupération des valeurs
       String Num = Number.getValue();
       String tit = title.getValue();
       String mess = msgbox.getValue();
       Object[] selcyle= cycles.getSelection().iterator().next();
       Object[] selfilter1 = filter1items.getSelection().iterator().next();
       Object[] selfilter2= filter2items.getSelection().iterator().next();
       String apercu = mess.length()> 40 ? mess.substring(0, 40)+"...":mess;
       String dateheure = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
       if(apply.getLabel().equals("Enregistrer")){
           apply.setLabel("Modifier");
           Number.setDisabled(true);
           title.getValue();
           filter1.setDisabled(true);
           filter2.setDisabled(true);
           cycle.setDisabled(true);
           title.setDisabled(true);
           msgbox.setCustomConfigurationsPath("/files/ckeditorconfig_disable.js");
           listitems.add(new Object[]{tit , apercu,  dateheure });
           
       }else{
           apply.setLabel("Enregistrer");
           Number.setDisabled(false);
           title.setDisabled(false);
           filter1.setDisabled(false);
           filter2.setDisabled(false);
           cycle.setDisabled(false);
           msgbox.setCustomConfigurationsPath("/files/ckeditorconfig.js");
       }

    }
  
    @Listen("onSelect = #msglistbox")
    public void onselectNew(){
        //nettoyage des valeurs
        if(listitems.getSelection().iterator().next().length == 1){
        Number.setValue("");
        cycle.setValue("");
        title.setValue("");
        filter1items.clear();
        filter2items.clear();
        msgbox.setValue("");
        cycles.clearSelection();
        //activation des champs 
        Number.setDisabled(false);
        title.setDisabled(false);
        cycle.setDisabled(false);
        filter1.setDisabled(false);
        filter2.setDisabled(false);
        msgbox.setCustomConfigurationsPath("/files/ckeditorconfig.js");
        apply.setLabel("Enregistrer");
        }else{
            
        //desactivation des champs 
        Number.setDisabled(true);
        title.setDisabled(true);
        cycle.setDisabled(true);
        filter1.setDisabled(true);
        filter2.setDisabled(true);
        msgbox.setCustomConfigurationsPath("/files/ckeditorconfig_disable.js");
        apply.setLabel("Modifier");
        }
    }
    
  
    
}