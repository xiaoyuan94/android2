package com.xxyuan.project.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xxyuan.project.ui.database.model.Student;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STUDENT".
*/
public class StudentDao extends AbstractDao<Student, Long> {

    public static final String TABLENAME = "STUDENT";

    /**
     * Properties of entity Student.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Age = new Property(2, int.class, "age", false, "AGE");
        public final static Property Num = new Property(3, String.class, "num", false, "NUM");
        public final static Property Info = new Property(4, String.class, "info", false, "INFO");
        public final static Property Text01 = new Property(5, String.class, "text01", false, "TEXT01");
        public final static Property Text02 = new Property(6, String.class, "text02", false, "TEXT02");
    }


    public StudentDao(DaoConfig config) {
        super(config);
    }
    
    public StudentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STUDENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"AGE\" INTEGER NOT NULL ," + // 2: age
                "\"NUM\" TEXT," + // 3: num
                "\"INFO\" TEXT," + // 4: info
                "\"TEXT01\" TEXT," + // 5: text01
                "\"TEXT02\" TEXT);"); // 6: text02
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STUDENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Student entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getAge());
 
        String num = entity.getNum();
        if (num != null) {
            stmt.bindString(4, num);
        }
 
        String info = entity.getInfo();
        if (info != null) {
            stmt.bindString(5, info);
        }
 
        String text01 = entity.getText01();
        if (text01 != null) {
            stmt.bindString(6, text01);
        }
 
        String text02 = entity.getText02();
        if (text02 != null) {
            stmt.bindString(7, text02);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Student entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getAge());
 
        String num = entity.getNum();
        if (num != null) {
            stmt.bindString(4, num);
        }
 
        String info = entity.getInfo();
        if (info != null) {
            stmt.bindString(5, info);
        }
 
        String text01 = entity.getText01();
        if (text01 != null) {
            stmt.bindString(6, text01);
        }
 
        String text02 = entity.getText02();
        if (text02 != null) {
            stmt.bindString(7, text02);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Student readEntity(Cursor cursor, int offset) {
        Student entity = new Student( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2), // age
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // num
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // info
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // text01
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // text02
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Student entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAge(cursor.getInt(offset + 2));
        entity.setNum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setInfo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setText01(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setText02(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Student entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Student entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Student entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
