
using System.Collections.Generic;

namespace WorkIt_Server.Models.DTO
{
    public class TaskRequestDTO
    {
        public int TaskRequestId { get; set; }
        public int TaskId { get; set; }
        public int UserId { get; set; }
        public string Description { get; set; }
        public int RequestStatusId { get; set; }
    }
}