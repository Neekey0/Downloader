//package MainGUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.BufferedInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.sql.SQLOutput;
//import java.util.Enumeration;
//import java.util.Properties;
//
//public class MainGUI {
//
//        private  JFrame jFrame;
//        private  JButton downloadBtn;
//        private  JLabel textBoxLabel;
//        private  JTextField downloadUrlField;
//        private  JPanel jPanelOne;
//
//        MainGUI()
//        {
//
//            loadGUIComponents();
//        }
//
//        public void loadGUIComponents()
//        {
//            jFrame = new JFrame("YouTube Downloader");
//            jFrame.setSize(500,500);
//
//            textBoxLabel= new JLabel("Please paste your youtube url ::: ");
//            downloadUrlField=new JTextField();
//            downloadBtn = new JButton("DOWNLOAD");
//            downloadBtn.addActionListener((ActionEvent e)->{
//                String userText = downloadUrlField.getText();
//                startDownload(userText);
//                JOptionPane.showMessageDialog(jFrame,"User input:"+ userText);
//            });
//
//            jPanelOne=new JPanel();
//
//            jPanelOne.add(textBoxLabel);
//            jPanelOne.add(downloadUrlField);
//            jPanelOne.setLayout(new GridLayout(1,1));
//            //jFrame.add(textBoxLabel);
//            //jFrame.add(downloadUrlField);
//
//            jFrame.add(jPanelOne);
//            jFrame.add(downloadBtn);
//
//            jFrame.setLayout(new GridLayout(2,1));
//
//            jFrame.setVisible(true);
//
//
//        }
//
//        private void startDownload(String downloadUrl) {
//            StringBuffer userHome= new StringBuffer(System.getProperty("user.home"));
//            StringBuffer fileSeparator = new StringBuffer("file.separator");
//            StringBuffer downloadPath = userHome.append(fileSeparator).append("Downloads").append(fileSeparator);
//            downloadPath.append(downloadUrl.substring(downloadUrl.lastIndexOf("/")+1);
//            URL url;
//            try {
//                url = new URL(downloadUrl);
//                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
//                FileOutputStream fileOutputStream = new FileOutputStream("test");
//                byte[] b = new byte[1024];
//                int count;
//
//                while ((count = bufferedInputStream.read(b, 0, 1024)) != -1) {
//                    fileOutputStream.write(b, 0, count);
//                }
//            } catch (MalformedURLException malformedURLException) {
//                System.out.println("The url provided:" + downloadUrl + "is invalid.");
//            } catch (IOException ioException) {
//                System.out.println("The resource is not found");
//            }
//        }
//        public static void main(String[] args)
//            {
//                System.out.println(System.getProperty("user.home"));
//
//                System.out.println(System.getProperty("file.separator"));
//
//               // System.out.println(System.getenv("OS"));
//
//                //System.out.println(System.getenv("Number_Of_Processors"));
//
//                Properties properties = System.getProperties();
//
//                Enumeration<?> enumeration =properties.propertyNames();
//
//                while(enumeration.hasMoreElements())
//
//                {
//                    String property = enumeration.nextElement().toString();
//                    System.out.println(property);
//                }
//
//                //  MainGUI mainGUI =new MainGUI();
//            }
//
//}

package MainGUI;

        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.io.BufferedInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.Enumeration;
        import java.util.Properties;
//import java.awt.event.ActionListener;

public class MainGUI {

    private  JFrame jFrame;
    private JLabel textBoxLabel;

    private JTextField downloadUrlField;

    private JPanel jPanelOne;

    private JProgressBar downloadProgress;
    MainGUI(){
        loadGUIComponents();
    }
    public void loadGUIComponents(){
        jFrame = new JFrame("Youtube downloader");
        jFrame.setSize(500,600);

        textBoxLabel = new JLabel("PLease paste your youtube url below:");
        downloadUrlField = new JTextField();
        JButton downloadBtm = new JButton("Download");
        downloadBtm.addActionListener((ActionEvent e) ->{
            String userText = downloadUrlField.getText();
            startDownload(userText);
            JOptionPane.showMessageDialog(jFrame,"User input : "+userText);
        });
        downloadProgress = new JProgressBar();
downloadProgress.setString("22");
        jPanelOne = new JPanel();
        jPanelOne.add(textBoxLabel);
        jPanelOne.add(downloadUrlField);
        jPanelOne.setLayout(new GridLayout(1,1));

        jFrame.add(jPanelOne);
        jFrame.add(downloadBtm);
        jFrame.add(downloadProgress);

        jFrame.setLayout(new GridLayout(3,1));
        jFrame.setVisible(true);

    }

    public void startDownload(String downlaodUrl) {
        // String a = "abcd";
        // String b = "abcd"; b = "cde"
        // url : https://somewebsite.com/downlaods/idm.exe .substring(0,4)
        StringBuffer userHome = new StringBuffer(System.getProperty("user.home"));
        StringBuffer fileSeparator = new StringBuffer(System.getProperty("file.separator"));
        StringBuffer downloadPath = userHome.append(fileSeparator).append("Downloads").append(fileSeparator);
        downloadPath.append(downlaodUrl.substring(downlaodUrl.lastIndexOf("/")+1));

        URL url;
        try {
            url = new URL(downlaodUrl);
            getFileTotalSize(url);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(downloadPath.toString());
            byte[] b = new byte[1024];
            int count;

            while((count= bufferedInputStream.read(b,0,1024))!=-1){
                fileOutputStream.write(b,0,count);
            }
        }catch(MalformedURLException malformedURLException){
            System.out.println("The url provided : "+downlaodUrl+ " is invalid");
        }catch (IOException ioException){
            System.out.println("The resource is not found");
        }
    }

    private void getFileTotalSize(URL url)
    {
        HttpURLConnection httpURLConnection;

        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();
            long length = httpURLConnection.getContentLengthLong();
            System.out.println("The size is" + length);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    //link ="https://az764295.vo.msecnd.net/stable/e2816fe719a4026ffa1ee0189dc89bdfdbafb164/VSCodeUserSetup-x64-1.75.0.exe";
    public static void main(String[] args) {
        // String downloadUrl =  "https://az764295.vo.msecnd.net/stable/e2816fe719a4026ffa1ee0189dc89bdfdbafb164/VSCodeUserSetup-x64-1.75.0.exe";
         //  PrintAllProperties();
        MainGUI mainGUI = new MainGUI();
       // startDownload(downloadUrl);
    }

    private static void PrintAllProperties() {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getenv("OS"));
        System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));
        Properties properties = System.getProperties();
        Enumeration<?> enumeration = properties.propertyNames();
        while(enumeration.hasMoreElements()){
            String property = enumeration.nextElement().toString();
            System.out.println(property +" "+System.getProperty(property));
        }
    }
}
