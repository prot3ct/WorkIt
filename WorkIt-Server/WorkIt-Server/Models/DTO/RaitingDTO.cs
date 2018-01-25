namespace WorkIt_Server.Models.DTO
{
    public class RaitingDTO
    {
        public int GiverUserId { get; set; }

        public int ReceiverUserId { get; set; }

        public int TaskId { get; set; }

        public int ReceiverUserRoleId { get; set; }

        public int Value { get; set; }
    }
}