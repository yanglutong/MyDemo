package com.example.mvplibrary.base.presenter;


import com.example.mvplibrary.base.model.BaseModel;
import com.example.mvplibrary.base.view.BaseView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base
 * 文件名：BasePresenterIml
 * 创建者：杨路通
 * 创建时间：2020/11/7  9:53
 * 描述：TODO
 */
public class BasePresenterIml<V extends BaseView,M extends BaseModel> implements BasePresenter<V>{
    private WeakReference<V> mWeakReferenceView;
    private WeakReference<M> mWeakReferenceModel;
    //视图
    private V mView;

    //model对象
    private M mModel;

    @Override
    public void bindView(V v) {
        mWeakReferenceView=new WeakReference<V>(v);
        if(v!=null&&mWeakReferenceView!=null){
            mView=mWeakReferenceView.get();
        }
        if(createModel()!=null){
            mWeakReferenceModel=new WeakReference<M>(createModel());
            mModel=mWeakReferenceModel.get();
        }

    }

    //根据泛型动态创建model对象
    private M createModel(){
        M mModel=null;
        Class<? extends BasePresenterIml> aClass = this.getClass();
        ParameterizedType genericSuperclass = (ParameterizedType) aClass.getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class actualTypeArgument = (Class) actualTypeArguments[1];
        if(actualTypeArgument!=null){
            try {
                mModel= (M) actualTypeArgument.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mModel;
    }

    public LifecycleProvider getLifecycleProvider(){
        if(mView!=null){
            return (LifecycleProvider) mView;
        }
        return null;
    }
    @Override
    public void onUnBindView() {
        if(mWeakReferenceView!=null){
            mWeakReferenceView.clear();
        }
        if(mWeakReferenceModel!=null){
            mWeakReferenceModel.clear();
        }
        if(mView!=null){
            mView=null;
        }
        if(mModel!=null){
            mModel=null;
        }
    }

    public V getView() {
        return mView;
    }

    public M getModel(){
        return mModel;
    }

}
