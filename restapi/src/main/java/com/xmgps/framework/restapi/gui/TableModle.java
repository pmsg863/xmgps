package com.xmgps.framework.restapi.gui;

import com.xmgps.framework.restapi.parser.MetaData;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gps_hwb on 2015/8/24.
 */
public class TableModle extends AbstractTableModel {

    public List<MetaData> inputParams = new ArrayList<>();

    public void setData( List<MetaData> data){
        inputParams = data;
        this.fireTableStructureChanged();
        this.fireTableDataChanged();
    }
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Name";
            case 1:
                return "MetaClass";
            default:
                break;
        }
        return null;
    }
    @Override
    public int getRowCount() {
        return inputParams.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MetaData metaData = inputParams.get(rowIndex);
        switch (columnIndex){
            case 0:
                return metaData.getName();
            case 1:
                return metaData.getMetaClass();
            default:
                break;
        }
        return null;
    }
}
