## Insert Interval

Given a set of *non-overlapping* intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted to their start times.

**Example 1:**

	Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
	Output: [[1,5],[6,9]]

**Example 2:**

	Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
	Output: [[1,2],[3,10],[12,16]]
	Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

#### Java Solution:

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
	    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
	        intervals.add(newInterval);
	        Collections.sort(intervals, (a, b) -> a.start-b.start);
	        Stack<Interval> res = new Stack<Interval>();
	        res.add(new Interval(intervals.get(0).start, intervals.get(0).end));
	        for(Interval interval : intervals){
	            Interval pre = res.peek();
	            if(interval.start<=pre.end){
	                pre.end=Math.max(interval.end, pre.end);
	            }else{
	                res.add(new Interval(interval.start, interval.end));
	            }
	        }
	        return res;
	    }
	}


#### Java Discussion Solution:

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
	    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
	        List<Interval> res = new ArrayList<Interval>();
	        for(int i=0; i<intervals.size(); i++){
	        	Interval interval = intervals.get(i);
	        	if(interval.end<newInterval.start){
	        		res.add(interval);
	                continue;
	        	}else if(interval.start>newInterval.end){
	        		res.add(newInterval);
	        		res.addAll(intervals.subList(i, intervals.size()));
	                return res;
	        	}else{
	        		newInterval.start = Math.min(newInterval.start, interval.start);
	        		newInterval.end = Math.max(newInterval.end, interval.end);
	        	}
	        }
	        res.add(newInterval);
	        return res;
	    }
	}

#### Python Discussion Solution :
	
	# Definition for an interval.
	# class Interval:
	#     def __init__(self, s=0, e=0):
	#         self.start = s
	#         self.end = e

	class Solution:
	    def insert(self, intervals, newInterval):
	        """
	        :type intervals: List[Interval]
	        :type newInterval: Interval
	        :rtype: List[Interval]
	        """
	        start = newInterval.start
	        end = newInterval.end
	        # Find left i.e. the index of the smallest overlapped number
	        left = 0
	        while left < len(intervals) and start > intervals[left].end:
	            left += 1
	
	        # Find right i.e. the index of the smallest not overlapped number
	        # During this also update the new interval according to overlaps
	        right = left
	        while right < len(intervals) and end >= intervals[right].start:
	            start = min(start, intervals[right].start)
	            end = max(end, intervals[right].end)
	            right += 1
	
	        return intervals[:left] + [Interval(start, end)] + intervals[right:]