using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WorkIt_Server.Models
{
    public class UserReport
    {
        [Key]
        public int UserReportId { get; set; }

        [Required]
        public string Description { get; set; }

        [Required]
        [ForeignKey("AuthorUser")]
        public int AuthorUserId { get; set; }
        public virtual User AuthorUser { get; set; }

        [Required]
        [ForeignKey("TargetUser")]
        public int TargetUserId { get; set; }
        public virtual User TargetUser { get; set; }
    }
}