package com.example.mydemo.utils;

public class GeneriArrayUtils {
    //创建一个动态数组
    private Object[] arr;

    //数据的一个元素下标
    private int index;

    //创建可以为数组动态值的构造
    public GeneriArrayUtils(int count){
        arr=new Object[count];
    }

    //包含四个方法增删改查
    //添加方法 参数类型为数组下标  数组里的某一个元素
    public void insertArray(int index,Object o){
        if(isCheckIndex(index)){
                //集合的长度等于最长的下标
                if(this.index==arr.length){
                    //扩容
                    setCapacity(2*index);
                }
                for (int i = arr.length-2; i >=index ; i--) {
                    arr[i+1]=arr[i];
                }
                arr[index]=o;
        }
        this.index++;
    }
    public void deleteArray(int index){
        if(arr[index]!=null){
            for (int i = index; i <arr.length-1 ; i++) {
                //从要删除的下标开始遍历到最后一位
                arr[i]=arr[i+1];
            }
            //删除最后一个下标的元素
            Object[] d=new Object[arr.length-1];
            for (int i = 0; i <arr.length-1 ; i++) {
                d[i]=arr[i];
            }
            arr=d;
        }
    }
    //修改里面的某一个元素
    public void updateArray(int index,Object o){
            arr[index]=o;
    }
    public void selectArray(){
        if(arr.length>0)
            for (int i = 0; i <this.arr.length ; i++) {
                System.out.println(arr[i]);
            }

        else{
            System.out.println("数组数据为空"+arr.length);
        }

    }

    //判断下标是否越界
    public boolean isCheckIndex(int index){
        //判断数组的下标为0或者下标大于总长度
        if(index<0||this.index<index){
            return false;
        }
        return true;
    }

    //当数组的长度不够调用此方法扩容
    private void setCapacity(int newCapacity){
            Object[] newData=new Object[newCapacity];
        for (int i=0; i<arr.length; i++){
            newData[i] =arr[i];
        }
        arr=newData;
    }

    //测试
    public void test(){
        //数组的长度为15
        GeneriArrayUtils arrayUtils = new GeneriArrayUtils(15);
        for (int i = 0; i <10 ; i++) {
            arrayUtils.insertArray(i,"元素"+i);
        }
        arrayUtils.selectArray();

        arrayUtils.deleteArray(2);

        arrayUtils.selectArray();

        arrayUtils.updateArray(5,"杨路通");

        arrayUtils.selectArray();
    }
}
