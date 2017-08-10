package com.learning.guava;

import com.google.common.base.*;
import com.google.common.cache.*;
import com.google.common.collect.*;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PreconditionsUsage {
    public static void main(String[] args) {
        String inputName = "i am colin";
        String name = Preconditions.checkNotNull(inputName);
//        Preconditions.checkNotNull(null);

         inputName = "1";
        Preconditions.checkArgument(inputName!=null && !"".equals(inputName),"input is null");

        Optional<String> optionalName = Optional.of("");
        if(optionalName.isPresent()){
            System.out.println("output:"+optionalName.get());
        }
        System.out.println(new PreconditionsUsage().toString());
        List<String> names = Lists.newArrayList();
        names.add("colin");
        names.add("chen");
        StringBuilder sb = new StringBuilder();
        String rs = Joiner.on(" ").appendTo(sb, names).toString();
        System.out.println(rs);

        String s = "dd  sfsfs  , dsfsf,ssfdfsdffsdfsf.sdfsfs,msfds";
        for(String str : Splitter.on(",").trimResults().split(s)){
            System.out.println(str);
        }

        ImmutableMap<Integer, String> map = ImmutableMap.of(122,"iamzhongyong",1222,"bixiao.zy");
        System.out.println(map.toString());



        Multimap<Integer, String> keyValues = ArrayListMultimap.create();
        keyValues.put(1, "a");
        keyValues.put(1, "b");
        keyValues.put(2, "c");
        System.out.println(keyValues.toString());

        HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);

        Sets.SetView union = Sets.union(setA, setB);
        System.out.println("union::"+union);

        Sets.SetView difference = Sets.difference(setA, setB);
        System.out.println("difference:"+difference);

        Sets.SetView intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:"+intersection);

//        public void getNameFromLocalCache() throws Exception{
            //new一个cache的对象出来
            Cache<String/*name*/,String/*nick*/> cache = CacheBuilder.newBuilder().maximumSize(10).build();
            //在get的时候，如果缓存里面没有，则通过实现一个callback的方法去获取
        String cacheName = null;
        try {
            cacheName = cache.get("bixiao", new Callable<String>() {
                public String call() throws Exception {
                    return "bixiao.zy"+"-"+"iamzhongyong";
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(cacheName);
            System.out.println(cache.toString());
        try {
            new PreconditionsUsage().getNameLoadingCache("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getNameLoadingCache(String name) throws Exception{
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                //设置大小，条目数
                .maximumSize(20)
                //设置失效时间，创建时间
                .expireAfterWrite(20, TimeUnit.SECONDS)
                //设置时效时间，最后一次被访问
                .expireAfterAccess(20, TimeUnit.HOURS)
                //移除缓存的监听器
                .removalListener(new RemovalListener<String, String>() {
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println("有缓存数据被移除了");
                    }})
                //缓存构建的回调
                .build(new CacheLoader<String, String>(){//加载缓存
                    @Override
                    public String load(String key) throws Exception {
                        return key + "-" + "iamzhongyong";
                    }
                });

        System.out.println(cache.get(name));
        cache.invalidateAll();
    }

    public String name="123";
    public int age=12;
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("age", this.age).toString();
    }
}
