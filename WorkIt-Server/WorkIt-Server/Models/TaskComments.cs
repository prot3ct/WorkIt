using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WorkIt_Server.Models
{
    public class TaskComments
    {
        [Key]
        public int TaskCommentsId { get; set; }

        [ForeignKey("Task")]
        public int? TaskId { get; set; }
        public virtual Task Task { get; set; }

        [ForeignKey("TaskRequest")]
        public int? TaskRequestId { get; set; }
        public virtual Task TaskRequest { get; set; }

        [Required]
        [ForeignKey("Comment")]
        public int CommentId { get; set; }
        public virtual Comment Comment { get; set; }
    }
}