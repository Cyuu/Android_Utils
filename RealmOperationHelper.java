package com.hht.realmo.realmdemo.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
/**
 * @author Realmo
 * @version 1.0.0
 * @name RealmOperationHelper
 * @email momo.weiye@gmail.com
 * @time 2018/1/22 14:24
 * @describe
 */

public class RealmOperationHelper {
    private static Realm mRealm;

    private static class SingletonHolder {
        private static RealmOperationHelper INSTANCE = new RealmOperationHelper(
                mRealm);
    }

    private RealmOperationHelper(Realm realm) {
        this.mRealm = realm;

    }

    /**
     * ��ȡRealmOperation�ĵ���
     *
     * @param realm ����realmʵ������
     * @return ����RealmOperation�ĵ���
     */
    public static RealmOperationHelper getInstance(Realm realm) {
        if (realm != null) {
            mRealm = realm;
        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * ���ӵ������ݵ����ݿ���
     *
     * @param bean ���ݶ��󣬱���̳���RealmObject
     */
    public void add(final RealmObject bean) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.copyToRealm(bean);

            }
        });

    }

    /**
     * ���Ӷ������ݵ����ݿ���
     *
     * @param beans ���ݼ��ϣ�����Ԫ�ر���̳���RealmObject
     */
    public void add(final List<? extends RealmObject> beans) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(beans);

            }
        });

    }
    /**
     * ���Ӷ������ݵ����ݿ���
     *
     * @param beans ���ݼ��ϣ�����Ԫ�ر���̳���RealmObject
     */
    public void addAsync(final List<? extends RealmObject> beans) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(beans);
            }
        });

    }

    /**
     * ɾ�����ݿ���clazz����������Ԫ��
     *
     * @param clazz
     */
    public void deleteAll(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteAllFromRealm();

            }
        });

    }
    /**
     * ɾ�����ݿ���clazz����������Ԫ��
     *
     * @param clazz
     */
    public void deleteAllAsync(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteAllFromRealm();

            }
        });


    }

    /**
     * ɾ�����ݿ���clazz��������һ��Ԫ��
     *
     * @param clazz
     */
    public void deleteFirst(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteFirstFromRealm();

            }
        });

    }

    /**
     * ɾ�����ݿ���clazz���������һ��Ԫ��
     *
     * @param clazz
     */
    public void deleteLast(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteLastFromRealm();

            }
        });

    }

    /**
     * ɾ�����ݿ���clazz������������ĳһλ�õ�Ԫ��
     *
     * @param clazz
     * @param position
     */
    public void deleteElement(Class<? extends RealmObject> clazz, final int position) {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteFromRealm(position);

            }
        });

    }

    /**
     * ��ѯ���ݿ���clazz��������������
     *
     * @param clazz
     * @return
     */
    public RealmResults<? extends RealmObject> queryAll(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();

        return beans;
    }
    /**
     * ��ѯ���ݿ���clazz��������������
     *
     * @param clazz
     * @return
     */
    public RealmResults<? extends RealmObject> queryAllAsync(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAllAsync();

        return  beans;
    }

    /**
     * ��ѯ���������ĵ�һ������
     *
     * @param clazz
     * @param fieldName
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public RealmObject queryByFieldFirst(Class<? extends RealmObject> clazz, String fieldName, String value) throws NoSuchFieldException {

        final RealmObject bean = mRealm.where(clazz).equalTo(fieldName, value).findFirst();

        return bean;
    }

    /**
     * ��ѯ������������������
     *
     * @param clazz
     * @param fieldName
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public RealmResults<? extends RealmObject> queryByFieldAll(Class<? extends RealmObject> clazz, String fieldName, String value) throws NoSuchFieldException {

        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).equalTo(fieldName, value).findAll();

        return beans;
    }
    /**
     * ��ѯ������������������
     *
     * @param clazz
     * @param fieldName
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public RealmResults<? extends RealmObject> queryByFieldAllAsync(Class<? extends RealmObject> clazz, String fieldName, String value) throws NoSuchFieldException {

        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).equalTo(fieldName, value).findAllAsync();

        return beans;
    }

    /**
     * ��ѯ���������ĵ�һ������
     *
     * @param clazz
     * @param fieldName
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public RealmObject queryByFieldFirst(Class<? extends RealmObject> clazz, String fieldName, int value) throws NoSuchFieldException {
        final RealmObject bean = mRealm.where(clazz).equalTo(fieldName, value).findFirst();

        return bean;
    }

    /**
     * ��ѯ������������������
     *
     * @param clazz
     * @param fieldName
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public RealmResults<? extends RealmObject> queryByFieldAll(Class<? extends RealmObject> clazz, String fieldName, int value) throws NoSuchFieldException {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).equalTo(fieldName, value).findAll();
        return beans;
    }
    /**
     * ��ѯ������������������
     *
     * @param clazz
     * @param fieldName
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public RealmResults<? extends RealmObject> queryByFieldAllAsync(Class<? extends RealmObject> clazz, String fieldName, int value) throws NoSuchFieldException {

        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).equalTo(fieldName, value).findAllAsync();
        return beans;
    }

    /**
     * ��ѯ���ݣ�����������
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public List<? extends RealmObject> queryAllByAscending(Class<? extends RealmObject> clazz, String fieldName) {
        RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();
        RealmResults<? extends RealmObject> results = beans.sort(fieldName, Sort.ASCENDING);
        return mRealm.copyFromRealm(results);
    }


    /**
     * ��ѯ���ݣ�����������
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public List<? extends RealmObject> queryAllByDescending(Class<? extends RealmObject> clazz, String fieldName) {
        RealmResults<? extends RealmObject> beans = mRealm.where(clazz).findAll();
        RealmResults<? extends RealmObject> results = beans.sort(fieldName, Sort.DESCENDING);
        return mRealm.copyFromRealm(results);
    }

    /**
     * ��������ĳ�������ĵ�һ�����ݵ�����ֵ
     * @param clazz
     * @param fieldName
     * @param oldValue
     * @param newValue
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void updateFirstByField(Class<? extends RealmObject> clazz, String fieldName,String oldValue,String newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmObject bean = mRealm.where(clazz).equalTo(fieldName, oldValue).findFirst();
        mRealm.beginTransaction();
        Method method = clazz.getMethod(fieldName, String.class);
        method.invoke(bean,newValue);
        mRealm.commitTransaction();

    }
    /**
     * ��������ĳ�������ĵ�һ�����ݵ�����ֵ
     * @param clazz
     * @param fieldName
     * @param oldValue
     * @param newValue
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void updateFirstByField(Class<? extends RealmObject> clazz, String fieldName,int oldValue,int newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmObject bean = mRealm.where(clazz).equalTo(fieldName, oldValue).findFirst();
        mRealm.beginTransaction();
        Method method = clazz.getMethod(fieldName, int.class);
        method.invoke(bean,newValue);
        mRealm.commitTransaction();

    }
    /**
     * ��������ĳ�������ĵ�һ�����ݵ�����ֵ
     * @param clazz
     * @param fieldName
     * @param oldValue
     * @param newValue
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void updateAllByField(Class<? extends RealmObject> clazz, String fieldName,String oldValue,String newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).equalTo(fieldName, oldValue).findAll();
        mRealm.beginTransaction();
        Method method = clazz.getMethod(fieldName, String.class);
        for(int i=0;i<beans.size();i++){
            RealmObject realmObject = beans.get(i);
            method.invoke(realmObject,newValue);
        }
        mRealm.commitTransaction();

    }
    /**
     * ��������ĳ�������ĵ�һ�����ݵ�����ֵ
     * @param clazz
     * @param fieldName
     * @param oldValue
     * @param newValue
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException9
     */
    public void updateAllByField(Class<? extends RealmObject> clazz, String fieldName, int oldValue, int newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmResults<? extends RealmObject> beans = mRealm.where(clazz).equalTo(fieldName, oldValue).findAll();
        mRealm.beginTransaction();
        Method method = clazz.getMethod(fieldName, int.class);
        for(int i=0;i<beans.size();i++){
            RealmObject realmObject = beans.get(i);
            method.invoke(realmObject,newValue);
        }
        mRealm.commitTransaction();

    }


}
