/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class WMain_Query {
    public static String location = "輔大";

    public static void main(String[] args) {
        AccountStation accountStation = new AccountStation(location);
        Scanner scan = new Scanner(System.in);
        String cardnumber;


        // 創建主框架
        JFrame frame = new JFrame("Rental 查詢");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 650);
        frame.setLayout(new BorderLayout(10, 10));

        // 上方面板 (輸入欄位)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("查詢條件"));
        inputPanel.setPreferredSize(new Dimension(1080, 150));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel cardLabel = new JLabel("優游卡號碼:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(cardLabel, gbc);

        JTextField cardField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(cardField, gbc);

        JLabel timeLabel = new JLabel("查詢時間:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(timeLabel, gbc);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton oneWeek = new JRadioButton("一周內");
        JRadioButton oneMonth = new JRadioButton("一個月內");
        JRadioButton sixMonths = new JRadioButton("半年內");
        oneWeek.setSelected(true);

        ButtonGroup timeGroup = new ButtonGroup();
        timeGroup.add(oneWeek);
        timeGroup.add(oneMonth);
        timeGroup.add(sixMonths);

        radioPanel.add(oneWeek);
        radioPanel.add(oneMonth);
        radioPanel.add(sixMonths);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inputPanel.add(radioPanel, gbc);

        // 中間面板 (按鈕)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton queryButton = new JButton("確定");
        buttonPanel.add(queryButton);
        JButton logoutButton = new JButton("登出");
        buttonPanel.add(logoutButton);

        // 下方面板 (查詢結果)
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(0, 8, 5, 5)); // 動態行數，固定 8 列
        resultPanel.setBorder(BorderFactory.createTitledBorder("查詢結果"));

        JPanel titlePanel = new JPanel(new GridLayout(0, 8, 5, 5));
        titlePanel.add(new JLabel("帳號", SwingConstants.CENTER));
        titlePanel.add(new JLabel("卡號", SwingConstants.CENTER));
        titlePanel.add(new JLabel("租借日期時間", SwingConstants.CENTER));
        titlePanel.add(new JLabel("租借站號", SwingConstants.CENTER));
        titlePanel.add(new JLabel("租借地點", SwingConstants.CENTER));
        titlePanel.add(new JLabel("歸還日期時間", SwingConstants.CENTER));
        titlePanel.add(new JLabel("歸還站號", SwingConstants.CENTER));
        titlePanel.add(new JLabel("歸還地點", SwingConstants.CENTER));

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setPreferredSize(new Dimension(1080, 400));

        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(titlePanel, BorderLayout.NORTH);
        combinedPanel.add(scrollPane, BorderLayout.CENTER);

        // 添加元件到框架
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(combinedPanel, BorderLayout.SOUTH);

        // 查詢按鈕事件監聽器
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardField.getText().trim();
                String selectedTime = "";
                int queryRange = 0;

                if (oneWeek.isSelected()) {
                    selectedTime = "一周內";
                    queryRange = 1;
                } else if (oneMonth.isSelected()) {
                    selectedTime = "一個月內";
                    queryRange = 2;
                } else if (sixMonths.isSelected()) {
                    selectedTime = "半年內";
                    queryRange = 3;
                }

                System.out.println("queryRange " + queryRange);
                if (cardNumber.isEmpty() || selectedTime.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "請輸入卡號並選擇查詢時間！", "錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                System.out.println("cardNumber " + cardNumber);

                if (accountStation.checkValidityMember(cardNumber) == true) {    //確認卡號是否有效

                    System.out.println("有效卡");

                    // 清空結果面板的舊內容
                    resultPanel.removeAll();

                    Object[][] data = accountStation.getRentalRecords(cardNumber, queryRange);

                    if (data != null) {
                        for (Object[] row : data) {
                            for (Object cell: row) {
                                JLabel cellLabel = new JLabel(cell.toString(), SwingConstants.CENTER);
                                //cellLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // 添加灰色格線
                                resultPanel.add(cellLabel);
                            }
                        }
                        // 刷新結果面板
                        resultPanel.revalidate();
                        resultPanel.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "查無資料");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "無效卡");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardField.setText("");

                resultPanel.removeAll();
                resultPanel.revalidate();
                resultPanel.repaint();
            }
        });

        // 顯示框架
        frame.setVisible(true);
    }
}
