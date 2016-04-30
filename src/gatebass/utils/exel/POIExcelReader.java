/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.utils.exel;

import static gatebass.GateBass.databaseHelper;
import static gatebass.GateBass.server;
import gatebass.dataBase.tables.CarHistory;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.Companies;
import gatebass.dataBase.tables.History;
import gatebass.dataBase.tables.IndividualReplica;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.Manage;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.individual_insert.Fxml_Individual_Insert;
import static gatebass.fxml.individual_insert.Fxml_Individual_Insert.BEDONE_KART;
import static gatebass.fxml.individual_insert.Fxml_Individual_Insert.MOAF;
import static gatebass.fxml.individual_insert.Fxml_Individual_Insert.PAYAN_KHEDMAT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author reza
 */
public class POIExcelReader {

//    private int end_row = 2242;
    private int start_row = 1;

    /**
     * Creates a new instance of POIExcelReader
     */
    public POIExcelReader() {
    }

    /**
     * 41 This method is used to display the Excel content to command line. 42 *
     *
     * @param xlsPath
     */
    @SuppressWarnings("unchecked")
    public void displayFromExcel(String xlsPath) {
//        end_row = 2242;
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;
        int dd = 0;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            List<Individuals> individualses = new ArrayList<>();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }

                if (row.getRowNum() <= start_row) {
                    continue;
                }

                dd = row.getRowNum();
//                if (row.getRowNum() == 0
//                        || row.getRowNum() < 195 || row.getRowNum() > 250
//                        ) {
//                    continue;
//                }
                Individuals individuals = null;

// display row number in the console.
//                System.out.println("Row No.: " + row.getRowNum());
// once get a row its time to iterate through cells.
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();

//                    System.out.println("Cell No.: " + cell.getCellNum());

                    /*
                     * Now we will get the cell type and display the values
                     * accordingly.
                     */
                    switch (cell.getCellNum()) {
                        case 0:
                            individuals = new Individuals();
//                            individuals = new Individuals(Integer.parseInt(cell.getRichStringCellValue().getString()));
                            break;
                        case 1:
                            try {
                                individuals.setCard_id(((long) cell.getNumericCellValue()) + "");
                            } catch (Exception e) {
                            }
                            try {
                                individuals.setCard_id(cell.getRichStringCellValue().getString());
                            } catch (Exception e) {
                            }
                            break;
//                        case 2:
//                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
//                            }
//                            break;
                        case 3:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setFirst_name(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 4:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setLast_name(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 6:
                            try {
                                individuals.setNational_id(((long) cell.getNumericCellValue()) + "");
                            } catch (Exception e) {
                            }
                            try {
                                individuals.setNational_id(cell.getRichStringCellValue().getString());
                            } catch (Exception e) {
                            }

                            break;
                        case 10:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setFirst_name_ENG(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 16:
                            try {
                                individuals.setPostal_code(((long) cell.getNumericCellValue()) + "");
                            } catch (Exception e) {
                            }
                            try {
                                individuals.setPostal_code(cell.getRichStringCellValue().getString());
                            } catch (Exception e) {
                            }
                            break;
                        case 17:
                            try {
                                individuals.setId_number(((long) cell.getNumericCellValue()) + "");
                            } catch (Exception e) {
                            }
                            try {
                                individuals.setId_number(cell.getRichStringCellValue().getString());
                            } catch (Exception e) {
                            }
                            break;
                        case 18:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setBirth_day(databaseHelper.historyDao.getFirst("date", cell.getRichStringCellValue().getString().substring(2)));
                            }
                            break;
                        case 19:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setFather_first_name(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 20:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setBirth_state(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 21:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setIssued(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 22:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setStreet_address(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 23:
                            String ss = cell.getRichStringCellValue().getString();
                            individuals.setVeteran_status(BEDONE_KART);
                            if (!ss.isEmpty()) {
                                if (ss.contains("معاف")) {
                                    individuals.setVeteran_status(MOAF);
                                } else if (ss.contains("خدمت")) {
                                    individuals.setVeteran_status(PAYAN_KHEDMAT);
                                }
                            }
                            break;
                        case 25:
                            try {
                                individuals.setMobile(((long) cell.getNumericCellValue()) + "");
                            } catch (Exception e) {
                            }
                            try {
                                individuals.setMobile(cell.getRichStringCellValue().getString());
                            } catch (Exception e) {
                            }

                            break;
                        case 26:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setAcademic_degree(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 27:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setField_of_study(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 28:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setReligion(cell.getRichStringCellValue().getString());
                            }
                            break;
                        case 34:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setHave_soe_pishine(true);
                            }
                            break;
                        case 35:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                individuals.setComments(cell.getRichStringCellValue().getString());
                            }
                            break;
                    }

                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: {
                            // cell type numeric.
//                            System.out.println("Numeric value: " + cell.getNumericCellValue());
                            break;
                        }

                        case HSSFCell.CELL_TYPE_STRING: {
                            // cell type string.
                            HSSFRichTextString richTextString = cell.getRichStringCellValue();
//                            System.out.println("String value: " + richTextString.getString());
                            break;
                        }

                        default: {
                            // types other than String and Numeric.
//                            System.out.println("Type not supported.");
                            break;
                        }
                    }
                }
                String split = FileSystems.getDefault().getSeparator();

                individuals.setFilesPatch("data" + split + "1394" + split + dd / 50 + split + individuals.getNational_id() + split);
                File imageFile = new File("d://test//Images-Personal-Gatepass//" + individuals.getCard_id() + ".jpg");

                if (imageFile.exists()) {
                        individuals.setPicture_address(individuals.getNational_id() + "-pic");
                        copyImageFile(imageFile.getAbsolutePath(), server + individuals.getFilesPatch(), individuals.getPicture_address());
                        individuals.setPicture_address(individuals.getPicture_address() + getFileExtension(imageFile.getAbsolutePath()));
                }
                individualses.add(individuals);
//                databaseHelper.individualsDao.createOrUpdate(individuals, dd);
            }
            databaseHelper.individualsDao.insertList(individualses);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void compnaiesFromExcel(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check = false;
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }
                if (row.getRowNum() <= start_row) {
                    continue;
                }

                Companies companies = new Companies();

//                System.out.println("Row No.: " + row.getRowNum());
                String companyName = row.getCell(2).getRichStringCellValue().getString();
                if (!companyName.isEmpty()) {
                    Companies companiesTEMP = databaseHelper.companiesDao.getFirst("company_fa", companyName);
                    if (companiesTEMP == null) {
                        check = true;
                        companies.setCompany_fa(companyName);
                        companies.setActive(true);
                    }
                } else {
                    continue;
                }
                try {
                    String companyNameEn = row.getCell(9).getRichStringCellValue().getString();
                    if (!companyNameEn.isEmpty()) {
                        Companies companiesTEMP = databaseHelper.companiesDao.getFirst("company_en", companyNameEn);
                        if (companiesTEMP == null) {
                            companies.setCompany_en(companyNameEn);
                        }
                    }
                } catch (Exception e) {
                }

                if (check) {
//                    companieses.add(companies);
                    Manage mm = databaseHelper.manageDao.getFirst("key", "company_folder_count");
                    int jj = Integer.parseInt(mm.getValue());
                    companies.setFolder_name("C" + jj);
                    databaseHelper.companiesDao.createOrUpdate(companies);
                    ++jj;
                    mm.setValue(jj + "");
                    databaseHelper.manageDao.createOrUpdate(mm);
                }
            }
//            databaseHelper.companiesDao.insertList(companieses);
        } catch (Exception e) {
            Logger.getLogger(POIExcelReader.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @SuppressWarnings("unchecked")
    public void historyFromExcel(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        try {
            fileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check;
            List<History> historys = new ArrayList<>();
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();
                if (row.getRowNum() <= start_row) {
                    continue;
                }
                History historyH = null;

//                System.out.println("Row No.: " + row.getRowNum());
                String history = "";

                try {
                    history = row.getCell(7).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(18).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(24).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(29).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(30).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(31).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(32).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(33).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }
            }
            databaseHelper.historyDao.insertList(historys);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void worksFromExcel(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        List<WorkHistory> historys = new ArrayList<>();
        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check = false;
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }
                if (row.getRowNum() <= start_row) {
                    continue;
                }

                WorkHistory workHistory = new WorkHistory();

//                System.out.println("Row No.: " + row.getRowNum());
                String row_value = row.getCell(2).getRichStringCellValue().getString();
                if (!row_value.isEmpty()) {
                    Companies companiesTEMP = databaseHelper.companiesDao.getFirst("company_fa", row_value);
                    String ss1 = companiesTEMP.getCompany_fa();
                    if (ss1.equals("پتروشیمی مخازن سبز عسلویه") || ss1.equals("بازرسی مهندسی و صنعتی ایران") || ss1.equals("تلاش کاران ماهر") || ss1.equals("سمت وسوی توسعه ایرانیان") || ss1.equals("کنکاوان معدن شیراز")) {
                        workHistory.setGate_type(WorkHistory.EMPLOYER);
                    } else {
                        workHistory.setGate_type(WorkHistory.CONTRACTOR);
                    }

                    workHistory.setCompanies(companiesTEMP);
                }

                row_value = row.getCell(5).getRichStringCellValue().getString();
                if (!row_value.isEmpty()) {
                    workHistory.setJobTitle(row_value);
                }

                try {
                    row_value = row.getCell(7).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        workHistory.setCardExpirationDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(11).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        workHistory.setJobTitleENG(row_value);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(24).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        workHistory.setEmploymentDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(29).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        workHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(30).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        workHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(31).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        workHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(33).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);

                        workHistory.setCardDeliveryDate(HistoryTEMP);
                    }
                } catch (Exception e) {
                }
//                System.out.println("Row No.: " + row.getRowNum() + " CardDelivery.: " + (workHistory.getCardDeliveryDate() == null ? "null" : workHistory.getCardDeliveryDate().getDate()));

                try {
                    row_value = ((long) row.getCell(6).getNumericCellValue()) + "";
                    if (!row_value.isEmpty()) {
                        Individuals individuals = databaseHelper.individualsDao.getFirst("national_id", row_value);
                        workHistory.setIndividualsId(individuals);
                    }
                } catch (Exception e) {
                }
                try {
                    row_value = row.getCell(6).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        Individuals individuals = databaseHelper.individualsDao.getFirst("national_id", row_value);
                        workHistory.setIndividualsId(individuals);
                    }
                } catch (Exception e) {
                }

                historys.add(workHistory);

//                    databaseHelper.manageDao.createOrUpdate(mm);
            }
            databaseHelper.workHistoryDao.insertList(historys);
        } catch (Exception e) {
            Logger.getLogger(POIExcelReader.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public void copyImageFile(String fileaddress, String dir, String name) {
        File srcFile = new File(fileaddress);
        File destFile = new File(dir + name + getFileExtension(fileaddress));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException ex) {
            System.out.println("MY ERROR :: " + ex.getMessage());
            Logger.getLogger(Fxml_Individual_Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getFileExtension(String fileaddress) {
        try {
            return fileaddress.substring(fileaddress.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

    @SuppressWarnings("unchecked")
    public void compnaiesFromExcel2(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check = false;
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }
                if (row.getRowNum() <= start_row) {
                    continue;
                }

                Companies companies = new Companies();

//                System.out.println("Row No.: " + row.getRowNum());
                String companyName = row.getCell(2).getRichStringCellValue().getString();
                if (!companyName.isEmpty()) {
                    Companies companiesTEMP = databaseHelper.companiesDao.getFirst("company_fa", companyName);
                    if (companiesTEMP == null) {
                        check = true;
                        companies.setCompany_fa(companyName);
                        companies.setActive(true);
                    }
                } else {
                    continue;
                }
                try {
                    String companyNameEn = row.getCell(9).getRichStringCellValue().getString();
                    if (!companyNameEn.isEmpty()) {
//                        Companies companiesTEMP = databaseHelper.companiesDao.getFirst("company_en", companyNameEn);
//                        if (companiesTEMP == null) {
//                            companies.setCompany_en(companyNameEn);
//                        }
                    }
                } catch (Exception e) {
                }

                if (check) {
//                    companieses.add(companies);
                    Manage mm = databaseHelper.manageDao.getFirst("key", "company_folder_count");
                    int jj = Integer.parseInt(mm.getValue());
                    companies.setFolder_name("C" + jj);
                    databaseHelper.companiesDao.createOrUpdate(companies);
                    ++jj;
                    mm.setValue(jj + "");
                    databaseHelper.manageDao.createOrUpdate(mm);
                }
            }
//            databaseHelper.companiesDao.insertList(companieses);
        } catch (Exception e) {
            Logger.getLogger(POIExcelReader.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @SuppressWarnings("unchecked")
    public void historyFromExcel2(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check = false;
            List<History> historys = new ArrayList<>();
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();

                History historyH = null;
                String history = "";
//                System.out.println("Row No.: " + row.getRowNum());

                try {
                    history = row.getCell(6).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(7).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(8).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(9).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(10).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(11).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(12).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(13).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }
            }
            databaseHelper.historyDao.insertList(historys);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void displayFromExcel2(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;
        int dd = 0;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            List<Cars> carses = new ArrayList<>();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }

                if (row.getRowNum() <= start_row) {
                    continue;
                }

                dd = row.getRowNum();
//                if (row.getRowNum() == 0
//                        || row.getRowNum() < 195 || row.getRowNum() > 250
//                        ) {
//                    continue;
//                }
                Cars cars = null;

// display row number in the console.
//                System.out.println("Row No.: " + row.getRowNum());
// once get a row its time to iterate through cells.
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    HSSFCell cell = (HSSFCell) cells.next();

//                    System.out.println("Cell No.: " + cell.getCellNum());

                    /*
                     * Now we will get the cell type and display the values
                     * accordingly.
                     */
                    switch (cell.getCellNum()) {
                        case 0:
                            cars = new Cars();
//                            individuals = new Individuals(Integer.parseInt(cell.getRichStringCellValue().getString()));
                            break;
                        case 1:
                            try {
                                cars.setCard_id(((long) cell.getNumericCellValue()) + "");
                            } catch (Exception e) {
                            }
                            try {
                                cars.setCard_id(cell.getRichStringCellValue().getString());
                            } catch (Exception e) {
                            }
                            cars.setShasi_number(cars.getCard_id());
                            break;
//                        case 2:
//                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
//                            }
//                            break;
                        case 3:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                cars.setCar_name(cell.getRichStringCellValue().getString());
                            }
                            break;
//                        case 4:
//                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
//                                cars.setLast_name(cell.getRichStringCellValue().getString());
//                            }
//                            break;
//                        case 5:
//                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
//                                cars.set(cell.getRichStringCellValue().getString());
//                            }
//                            break;
                        case 13:
                            if (!cell.getRichStringCellValue().getString().isEmpty()) {
                                cars.setComments(cell.getRichStringCellValue().getString());
                            }
                            break;
                    }

                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: {
                            // cell type numeric.
//                            System.out.println("Numeric value: " + cell.getNumericCellValue());
                            break;
                        }

                        case HSSFCell.CELL_TYPE_STRING: {
                            // cell type string.
                            HSSFRichTextString richTextString = cell.getRichStringCellValue();
//                            System.out.println("String value: " + richTextString.getString());
                            break;
                        }

                        default: {
                            // types other than String and Numeric.
//                            System.out.println("Type not supported.");
                            break;
                        }
                    }
                }
                String split = FileSystems.getDefault().getSeparator();

                cars.setFilesPatch("data" + split + "1394" + split + dd / 50 + split + cars.getShasi_number() + "_c" + split);
                carses.add(cars);
//                databaseHelper.individualsDao.createOrUpdate(individuals, dd);
            }
            databaseHelper.carDao.insertList(carses);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void worksFromExcel2(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        List<CarHistory> historys = new ArrayList<>();
        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check = false;
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }
                if (row.getRowNum() <= start_row) {
                    continue;
                }

                CarHistory carHistory = new CarHistory();

//                System.out.println("Row No.: " + row.getRowNum());
                String row_value = row.getCell(2).getRichStringCellValue().getString();
                if (!row_value.isEmpty()) {
                    Companies companiesTEMP = databaseHelper.companiesDao.getFirst("company_fa", row_value);
                    carHistory.setCompanies(companiesTEMP);
                }

                row_value = row.getCell(5).getRichStringCellValue().getString();
                if (!row_value.isEmpty()) {
                    carHistory.setPellak(row_value);
                }

                try {
                    row_value = row.getCell(6).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        carHistory.setCardExpirationDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(7).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        carHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(8).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        carHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(9).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        carHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(10).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        carHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(11).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        carHistory.setCardIssuedDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = row.getCell(12).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        row_value = row_value.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", row_value);
                        carHistory.setCardDeliveryDateId(HistoryTEMP);
                    }
                } catch (Exception e) {
                }

                try {
                    row_value = ((long) row.getCell(1).getNumericCellValue()) + "";
                    if (!row_value.isEmpty()) {
                        Cars cr1 = databaseHelper.carDao.getFirst("card_id", row_value);
                        carHistory.setCar_id(cr1);
                    }
                } catch (Exception e) {
                }
                try {
                    row_value = row.getCell(1).getRichStringCellValue().getString();
                    if (!row_value.isEmpty()) {
                        Cars cr1 = databaseHelper.carDao.getFirst("card_id", row_value);
                        carHistory.setCar_id(cr1);
                    }
                } catch (Exception e) {
                }

                historys.add(carHistory);

//                    databaseHelper.manageDao.createOrUpdate(mm);
            }
            databaseHelper.carsHistoryDao.insertList(historys);
        } catch (Exception e) {
            Logger.getLogger(POIExcelReader.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @SuppressWarnings("unchecked")
    public void historyFromExcel3(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check;
            List<History> historys = new ArrayList<>();
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();

                History historyH = null;
                String history = "";
//                System.out.println("Row No.: " + row.getRowNum());

                try {
                    history = row.getCell(7).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }
                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(10).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

                check = false;
                historyH = null;
                history = "";

                try {
                    history = row.getCell(11).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }

            }
            databaseHelper.historyDao.insertList(historys);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void displayFromExcel3(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;
        int dd = 0;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            List<Individuals> individualses = new ArrayList<>();

//            Manage manage = databaseHelper.manageDao.getFirst("key", "card_id_count");
//            long card_sequential = Long.parseLong(manage.getValue());
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }

                if (row.getRowNum() <= start_row) {
                    continue;
                }

                dd = row.getRowNum();
//                if (row.getRowNum() == 0
//                        || row.getRowNum() < 195 || row.getRowNum() > 250
//                        ) {
//                    continue;
//                }
                Individuals individuals = null;

// display row number in the console.
//                System.out.println("Row No.: " + row.getRowNum());
// once get a row its time to iterate through cells.
                String meli = "";

                {
                    try {
                        meli = (((long) row.getCell(6).getNumericCellValue()) + "");
                    } catch (Exception e) {
                    }
                    try {
                        meli = (row.getCell(6).getRichStringCellValue().getString());
                    } catch (Exception e) {
                    }
//                    System.out.println("meli = " + meli);
                    individuals = databaseHelper.individualsDao.getFirst("national_id", meli);
                    if (individuals == null) {

                        individuals = new Individuals();
                        individuals.setNational_id(meli);

//                        try {
//                            individuals.setCard_id(((long) row.getCell(0).getNumericCellValue()) + "");
//                        } catch (Exception e) {
//                        }
//                        try {
//                            individuals.setCard_id(row.getCell(0).getRichStringCellValue().getString());
//                        } catch (Exception e) {
//                        }
                        if (!row.getCell(1).getRichStringCellValue().getString().isEmpty()) {
                            individuals.setFirst_name(row.getCell(1).getRichStringCellValue().getString());
                        }
                        try {
                            if (!row.getCell(2).getRichStringCellValue().getString().isEmpty()) {
                                individuals.setFather_first_name(row.getCell(2).getRichStringCellValue().getString());
                            }
                        } catch (Exception e) {
                        }
                        try {
                            individuals.setId_number(((long) row.getCell(3).getNumericCellValue()) + "");
                        } catch (Exception e) {
                        }
                        try {
                            individuals.setId_number(row.getCell(3).getRichStringCellValue().getString());
                        } catch (Exception e) {
                        }
                        try {
                            if (!row.getCell(4).getRichStringCellValue().getString().isEmpty()) {
                                individuals.setBirth_day(databaseHelper.historyDao.getFirst("date", row.getCell(4).getRichStringCellValue().getString().substring(2)));
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (!row.getCell(5).getRichStringCellValue().getString().isEmpty()) {
                                individuals.setBirth_state(row.getCell(5).getRichStringCellValue().getString());
                            }
                        } catch (Exception e) {
                        }
                        try {
                            individuals.setComments("شماره کارت قدیم : " + ((long) row.getCell(0).getNumericCellValue()) + (individuals.getComments() != null ? "\n" + individuals.getComments() : ""));
                        } catch (Exception e) {
                        }
                        try {
                            individuals.setComments("شماره کارت قدیم : " + row.getCell(0).getRichStringCellValue().getString() + (individuals.getComments() != null ? "\n" + individuals.getComments() : ""));
                        } catch (Exception e) {
                        }

                        String split = FileSystems.getDefault().getSeparator();
                        individuals.setFilesPatch("data" + split + "1394" + split + dd / 50 + split + individuals.getNational_id() + split);
//                        individuals.setCard_id(card_sequential + "");
//                        card_sequential++;

                        individualses.add(individuals);
                    }

                }
//                databaseHelper.individualsDao.createOrUpdate(individuals, dd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void displayFromExcel35(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;
        int dd = 0;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            List<WorkHistory> whs = new ArrayList<>();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }

                if (row.getRowNum() <= start_row) {
                    continue;
                }

                dd = row.getRowNum();
//                if (row.getRowNum() == 0
//                        || row.getRowNum() < 195 || row.getRowNum() > 250
//                        ) {
//                    continue;
//                }
                Individuals individuals = null;

// display row number in the console.
//                System.out.println("Row No.: " + row.getRowNum());
// once get a row its time to iterate through cells.
                String meli = "";

                {
                    try {
                        meli = (((long) row.getCell(6).getNumericCellValue()) + "");
                    } catch (Exception e) {
                    }
                    try {
                        meli = (row.getCell(6).getRichStringCellValue().getString());
                    } catch (Exception e) {
                    }

//                    System.out.println("meli = " + meli);
                    individuals = databaseHelper.individualsDao.getFirst("national_id", meli);
                }
                whs.add(works_add(row, individuals));
            }
            databaseHelper.workHistoryDao.insertList(whs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private WorkHistory works_add(HSSFRow row, Individuals individuals) {
        WorkHistory wh = new WorkHistory();
        wh.setIndividualsId(individuals);
        if (!row.getCell(7).getRichStringCellValue().getString().isEmpty()) {
            wh.setEmploymentDateId(databaseHelper.historyDao.getFirst("date", row.getCell(7).getRichStringCellValue().getString().substring(2)));
        }
        if (!row.getCell(8).getRichStringCellValue().getString().isEmpty()) {
            wh.setJobTitle(row.getCell(8).getRichStringCellValue().getString());
        }

        String row_value = row.getCell(9).getRichStringCellValue().getString();
        if (!row_value.isEmpty()) {
            Companies companiesTEMP = databaseHelper.companiesDao.getFirst("company_fa", row_value);
            wh.setCompanies(companiesTEMP);
        }
        if (!row.getCell(10).getRichStringCellValue().getString().isEmpty()) {
            wh.setCardIssuedDateId(databaseHelper.historyDao.getFirst("date", row.getCell(10).getRichStringCellValue().getString().substring(2)));
        }
        if (!row.getCell(11).getRichStringCellValue().getString().isEmpty()) {
            wh.setCardExpirationDateId(databaseHelper.historyDao.getFirst("date", row.getCell(11).getRichStringCellValue().getString().substring(2)));
        }
        if (!row.getCell(13).getRichStringCellValue().getString().isEmpty()) {
            if (row.getCell(13).getRichStringCellValue().getString().equals("غیرفعال")) {
                wh.setCardDeliveryDate(wh.getCardExpirationDateId());
            }
        }
        return wh;
    }

    @SuppressWarnings("unchecked")
    public void history_replica(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            boolean check;
            List<History> historys = new ArrayList<>();
            while (rows.hasNext()) {
                check = false;
                HSSFRow row = (HSSFRow) rows.next();

                History historyH = null;
                String history = "";
//                System.out.println("Row No.: " + row.getRowNum());

                try {
                    history = row.getCell(1).getRichStringCellValue().getString();
                    if (!history.isEmpty()) {
                        history = history.substring(2);
                        History HistoryTEMP = databaseHelper.historyDao.getFirst("date", history);
                        if (HistoryTEMP == null) {
                            check = true;
                            historyH = new History(
                                    history.substring(0, history.indexOf("/")),
                                    history.substring(history.indexOf("/") + 1, history.lastIndexOf("/")),
                                    history.substring(history.lastIndexOf("/") + 1));
                        }
                    }
                    if (check) {
                        historys.add(historyH);
//                        databaseHelper.historyDao.createOrUpdate(historyH);
                    }
                } catch (Exception e) {
                }
            }
            databaseHelper.historyDao.insertList(historys);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public void replica_FromExcel(String xlsPath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(xlsPath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            e.printStackTrace();
        }

        POIFSFileSystem fileSystem = null;

        try {
            fileSystem = new POIFSFileSystem(inputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            List<IndividualReplica> individualReplicas = new ArrayList<>();

            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
//                if (row.getRowNum() >= end_row) {
//                    break;
//                }

                if (row.getRowNum() <= start_row) {
                    continue;
                }

//                if (row.getRowNum() == 0
//                        || row.getRowNum() < 195 || row.getRowNum() > 250
//                        ) {
//                    continue;
//                }
                IndividualReplica individualReplica = new IndividualReplica();

// display row number in the console.
//                System.out.println("Row No.: " + row.getRowNum());
// once get a row its time to iterate through cells.
                String meli = "";
                try {
                    meli = (((long) row.getCell(3).getNumericCellValue()) + "");
                } catch (Exception e) {
                }
                try {
                    meli = (row.getCell(3).getRichStringCellValue().getString());
                } catch (Exception e) {
                }
                Individuals individuals = databaseHelper.individualsDao.getFirst("card_id", meli);
                if (individuals != null) {
//                    individualReplica.setIndividual_id(individuals);

                    try {
                        if (!row.getCell(1).getRichStringCellValue().getString().isEmpty()) {
                            individualReplica.setHistory_id(databaseHelper.historyDao.getFirst("date", row.getCell(1).getRichStringCellValue().getString().substring(2)));
                        }
                    } catch (Exception e) {
                    }

                    try {
                        individualReplica.setMablagh(((long) row.getCell(5).getNumericCellValue()) + "");
                    } catch (Exception e) {
                    }
                    try {
                        individualReplica.setMablagh(row.getCell(5).getRichStringCellValue().getString());
                    } catch (Exception e) {
                    }

                    try {
                        individualReplica.setDescription(((long) row.getCell(6).getNumericCellValue()) + "");
                    } catch (Exception e) {
                    }
                    try {
                        individualReplica.setDescription(row.getCell(6).getRichStringCellValue().getString());
                    } catch (Exception e) {
                    }
                    individualReplicas.add(individualReplica);
                } else {
                    System.out.println("replica ID : " + meli);
                }
            }
            databaseHelper.individualReplicaDao.insertList(individualReplicas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
