
using System.Collections.Generic;

namespace WorkIt_Server.Models.DTO
{
    public class TaskRequestDTO
    {
        public int TaskRequestId { get; set; }
        public int TaskTitle { get; set; }
        public string Name { get; set; } 
        public string Description { get; set; }
        public ICollection<CommentDTO> Comments { get; set; }
    }
}