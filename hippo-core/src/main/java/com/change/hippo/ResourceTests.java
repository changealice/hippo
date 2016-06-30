package com.change.hippo;

/**
 * User: change.long
 * Date: 16/6/27
 * Time: 上午11:12
 */
public class ResourceTests {


    public static void main(String[] args) throws Exception {
        //原生的
        Resource resource = null;
        try {
            resource = new Resource();
            resource.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resource) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        try (Resource resource1 = new Resource()) {
            resource1.read();
        }
    }

    public static class Resource implements AutoCloseable {

        public void read() {
            System.out.println("Reading...");
        }

        @Override
        public void close() throws Exception {
            System.out.println("Resource Close");
        }
    }

}
