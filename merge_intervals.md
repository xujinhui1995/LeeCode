## Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

**Example 1:**

	Input: [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

**Example 2:**

	Input: [[1,4],[4,5]]
	Output: [[1,5]]
	Explanation: Intervals [1,4] and [4,5] are considerred overlapping.

#### Java Discussion Solution :

	import java.util.Collections;
	/**
	 * Definition for an interval.
	 * public class Interval {
	 *     int start;
	 *     int end;
	 *     Interval() { start = 0; end = 0; }
	 *     Interval(int s, int e) { start = s; end = e; }
	 * }
	 */
	class Solution {
	    public List<Interval> merge(List<Interval> intervals) {
	
	        if (intervals == null || intervals.size() < 1) {
	            return new LinkedList<>();
	        }
	        
	        Collections.sort(intervals, (a, b) -> a.start - b.start);
	
	        Stack<Interval> result = new Stack<>();
	        result.add(new Interval(intervals.get(0).start, intervals.get(0).end));
					
	        for (Interval current : intervals) {
	            Interval observe = result.peek();
	            if (current.start <= observe.end) {
	                observe.end = Math.max(observe.end, current.end);
	            } else {
	                result.add(new Interval(current.start, current.end));
	            }
	        }
	
	        return result;
	    }
	}

#### Python Discussion Solution :

	# Definition for an interval.
	# class Interval(object):
	#     def __init__(self, s=0, e=0):
	#         self.start = s
	#         self.end = e
	
	class Solution(object):
	    def merge(self, intervals):
	        """
	        :type intervals: List[Interval]
	        :rtype: List[Interval]
	        """
	        intervals = sorted(intervals, key = lambda x:x.start)
	        n = len(intervals)
	        if n==0:
	            return []
	        re = []
	        re.append(intervals[0])
	        for i in range(1, n):
	            pre = re[-1]
	            cur = intervals[i]
	            if cur.start > pre.end:
	                re.append(cur)
	            else:
	                re[-1].end = max(pre.end, cur.end)
	        return re