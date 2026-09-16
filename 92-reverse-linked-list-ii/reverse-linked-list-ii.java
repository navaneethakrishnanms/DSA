class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        
        // Create a dummy node to handle edge cases where left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Move "prev" to the node right before the "left" position
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        
        // "curr" is the first node of the sublist to be reversed
        ListNode curr = prev.next;
        
        // Reverse the sublist from left to right
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }
        
        return dummy.next;
    }
}
