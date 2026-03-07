package com.ducviet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Lớp kiểm thử đơn vị cho StudentAnalyzer.
 * Bao gồm các trường hợp: bình thường, biên, ngoại lệ.
 */
public class StudentAnalyzerTest {

    private StudentAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new StudentAnalyzer();
    }

    // ==========================================
    // Tests cho countExcellentStudents()
    // ==========================================

    @Nested
    @DisplayName("countExcellentStudents() - Trường hợp bình thường")
    class CountExcellentStudents_NormalCases {

        @Test
        @DisplayName("Danh sách có nhiều điểm hợp lệ và không hợp lệ")
        void testMixedValidAndInvalidScores() {
            // 9.0 >= 8.0 (giỏi), 8.5 >= 8.0 (giỏi), 7.0 < 8.0, 11.0 > 10 (bỏ qua), -1.0 < 0 (bỏ qua)
            List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
            assertEquals(2, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách toàn bộ hợp lệ - có cả giỏi và không giỏi")
        void testAllValidScores() {
            List<Double> scores = Arrays.asList(9.0, 5.5, 8.0, 6.0, 10.0);
            assertEquals(3, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách toàn bộ học sinh giỏi")
        void testAllExcellentScores() {
            List<Double> scores = Arrays.asList(8.0, 8.5, 9.0, 9.5, 10.0);
            assertEquals(5, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách không có học sinh giỏi nào")
        void testNoExcellentScores() {
            List<Double> scores = Arrays.asList(5.0, 6.0, 7.0, 7.9);
            assertEquals(0, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách có một phần tử duy nhất - giỏi")
        void testSingleExcellentScore() {
            List<Double> scores = Arrays.asList(9.0);
            assertEquals(1, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách có một phần tử duy nhất - không giỏi")
        void testSingleNonExcellentScore() {
            List<Double> scores = Arrays.asList(7.0);
            assertEquals(0, analyzer.countExcellentStudents(scores));
        }
    }

    @Nested
    @DisplayName("countExcellentStudents() - Trường hợp biên")
    class CountExcellentStudents_BoundaryCases {

        @Test
        @DisplayName("Danh sách trống")
        void testEmptyList() {
            assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()));
        }

        @Test
        @DisplayName("Danh sách chỉ chứa giá trị 0")
        void testListWithOnlyZero() {
            List<Double> scores = Arrays.asList(0.0, 0.0, 0.0);
            assertEquals(0, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách chỉ chứa giá trị 10")
        void testListWithOnlyTen() {
            List<Double> scores = Arrays.asList(10.0, 10.0, 10.0);
            assertEquals(3, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách chỉ chứa 0 hoặc 10")
        void testListWithZeroAndTen() {
            List<Double> scores = Arrays.asList(0.0, 10.0);
            assertEquals(1, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Điểm đúng biên 8.0 - nằm trong nhóm giỏi")
        void testExactBoundary8() {
            List<Double> scores = Arrays.asList(8.0);
            assertEquals(1, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Điểm ngay dưới biên 7.99 - không phải giỏi")
        void testJustBelow8() {
            List<Double> scores = Arrays.asList(7.99);
            assertEquals(0, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Điểm đúng biên 0.0 - hợp lệ nhưng không giỏi")
        void testExactBoundaryZero() {
            List<Double> scores = Arrays.asList(0.0);
            assertEquals(0, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Điểm đúng biên 10.0 - hợp lệ và giỏi")
        void testExactBoundaryTen() {
            List<Double> scores = Arrays.asList(10.0);
            assertEquals(1, analyzer.countExcellentStudents(scores));
        }
    }

    @Nested
    @DisplayName("countExcellentStudents() - Trường hợp ngoại lệ")
    class CountExcellentStudents_ExceptionalCases {

        @Test
        @DisplayName("Có điểm < 0 - bỏ qua điểm âm")
        void testNegativeScores() {
            List<Double> scores = Arrays.asList(-1.0, -5.0, 9.0);
            assertEquals(1, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Có điểm > 10 - bỏ qua điểm vượt 10")
        void testScoresAboveTen() {
            List<Double> scores = Arrays.asList(11.0, 15.0, 8.5);
            assertEquals(1, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Tất cả điểm không hợp lệ")
        void testAllInvalidScores() {
            List<Double> scores = Arrays.asList(-1.0, 11.0, -5.0, 100.0);
            assertEquals(0, analyzer.countExcellentStudents(scores));
        }

        @Test
        @DisplayName("Danh sách null")
        void testNullList() {
            assertEquals(0, analyzer.countExcellentStudents(null));
        }

        @Test
        @DisplayName("Danh sách chứa phần tử null")
        void testListWithNullElements() {
            List<Double> scores = new ArrayList<>();
            scores.add(9.0);
            scores.add(null);
            scores.add(8.5);
            assertEquals(2, analyzer.countExcellentStudents(scores));
        }
    }

    // ==========================================
    // Tests cho calculateValidAverage()
    // ==========================================

    @Nested
    @DisplayName("calculateValidAverage() - Trường hợp bình thường")
    class CalculateValidAverage_NormalCases {

        @Test
        @DisplayName("Danh sách có nhiều điểm hợp lệ và không hợp lệ")
        void testMixedValidAndInvalidScores() {
            // Hợp lệ: 9.0, 8.5, 7.0 -> trung bình = (9.0 + 8.5 + 7.0) / 3 = 8.17
            // Không hợp lệ: 11.0, -1.0 -> bỏ qua
            List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
            assertEquals(8.17, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Danh sách toàn bộ hợp lệ")
        void testAllValidScores() {
            List<Double> scores = Arrays.asList(7.0, 8.0, 9.0);
            assertEquals(8.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Danh sách có một phần tử hợp lệ")
        void testSingleValidScore() {
            List<Double> scores = Arrays.asList(7.5);
            assertEquals(7.5, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Danh sách nhiều điểm đa dạng")
        void testDiverseScores() {
            List<Double> scores = Arrays.asList(1.0, 3.0, 5.0, 7.0, 9.0);
            assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.01);
        }
    }

    @Nested
    @DisplayName("calculateValidAverage() - Trường hợp biên")
    class CalculateValidAverage_BoundaryCases {

        @Test
        @DisplayName("Danh sách trống")
        void testEmptyList() {
            assertEquals(0, analyzer.calculateValidAverage(Collections.emptyList()), 0.01);
        }

        @Test
        @DisplayName("Danh sách chỉ chứa giá trị 0")
        void testListWithOnlyZero() {
            List<Double> scores = Arrays.asList(0.0);
            assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Danh sách chỉ chứa giá trị 10")
        void testListWithOnlyTen() {
            List<Double> scores = Arrays.asList(10.0);
            assertEquals(10.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Danh sách chỉ chứa 0 và 10")
        void testListWithZeroAndTen() {
            List<Double> scores = Arrays.asList(0.0, 10.0);
            assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Tất cả điểm giống nhau")
        void testAllSameScores() {
            List<Double> scores = Arrays.asList(5.0, 5.0, 5.0);
            assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Điểm biên chính xác 0.0 và 10.0")
        void testExactBoundaryValues() {
            List<Double> scores = Arrays.asList(0.0, 10.0, 5.0);
            assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.01);
        }
    }

    @Nested
    @DisplayName("calculateValidAverage() - Trường hợp ngoại lệ")
    class CalculateValidAverage_ExceptionalCases {

        @Test
        @DisplayName("Có điểm < 0 hoặc > 10 - chỉ tính điểm hợp lệ")
        void testInvalidScoresFiltered() {
            // Hợp lệ: 5.0, 7.0 -> trung bình = 6.0
            List<Double> scores = Arrays.asList(-2.0, 5.0, 12.0, 7.0);
            assertEquals(6.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Tất cả điểm không hợp lệ - trả về 0")
        void testAllInvalidScores() {
            List<Double> scores = Arrays.asList(-1.0, 11.0, -5.0, 100.0);
            assertEquals(0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Danh sách null - trả về 0")
        void testNullList() {
            assertEquals(0, analyzer.calculateValidAverage(null), 0.01);
        }

        @Test
        @DisplayName("Danh sách chứa phần tử null - bỏ qua null")
        void testListWithNullElements() {
            List<Double> scores = new ArrayList<>();
            scores.add(6.0);
            scores.add(null);
            scores.add(8.0);
            assertEquals(7.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Điểm ngay ngoài biên dưới -0.01")
        void testJustBelowZero() {
            List<Double> scores = Arrays.asList(-0.01, 5.0);
            assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.01);
        }

        @Test
        @DisplayName("Điểm ngay ngoài biên trên 10.01")
        void testJustAboveTen() {
            List<Double> scores = Arrays.asList(10.01, 5.0);
            assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.01);
        }
    }
}
