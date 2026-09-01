class Solution {

    public void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode curr = slow.next;
        slow.next = null;

        ListNode prev = null;
        ListNode next = null;

        while (curr != null) {

            next = curr.next;

            curr.next = prev;

            prev = curr;

            curr = next;
        }

        ListNode lh = head;
        ListNode rh = prev;

        ListNode nxtL;
        ListNode nxtR;

        while (lh != null && rh != null) {

            nxtL = lh.next;
            nxtR = rh.next;

            lh.next = rh;

            rh.next = nxtL;

            lh = nxtL;
            rh = nxtR;
        }
    }
}