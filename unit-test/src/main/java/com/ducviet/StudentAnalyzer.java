package com.ducviet;

import java.util.List;

/**
 * Lớp phân tích điểm số học sinh.
 * Cung cấp các phương thức để đếm học sinh giỏi và tính trung bình điểm hợp lệ.
 */
public class StudentAnalyzer {

    /**
     * Phân tích điểm số và trả về số lượng học sinh đạt loại Giỏi.
     *
     * @param scores danh sách điểm số từ 0 đến 10
     * @return số học sinh đạt loại Giỏi (>= 8.0)
     * - Bỏ qua điểm âm hoặc lớn hơn 10 (coi là dữ liệu sai)
     * - Nếu danh sách rỗng, trả về 0
     */
    public int countExcellentStudents(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (Double score : scores) {
            if (score == null) {
                continue;
            }
            // Bỏ qua điểm không hợp lệ (< 0 hoặc > 10)
            if (score < 0 || score > 10) {
                continue;
            }
            // Đếm học sinh giỏi (>= 8.0)
            if (score >= 8.0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Tính điểm trung bình hợp lệ (từ 0 đến 10).
     *
     * @param scores danh sách điểm
     * @return điểm trung bình của các điểm hợp lệ
     * - Bỏ qua điểm nhỏ hơn 0 hoặc lớn hơn 10 (validate)
     * - Nếu danh sách rỗng hoặc không có điểm hợp lệ, trả về 0
     */
    public double calculateValidAverage(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0;
        }

        double sum = 0;
        int validCount = 0;

        for (Double score : scores) {
            if (score == null) {
                continue;
            }
            // Bỏ qua điểm không hợp lệ
            if (score < 0 || score > 10) {
                continue;
            }
            sum += score;
            validCount++;
        }

        if (validCount == 0) {
            return 0;
        }

        return sum / validCount;
    }
}
