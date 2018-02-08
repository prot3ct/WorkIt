using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WorkIt_Server.Models
{
    public class Task
    {
        [Key]
        public int TaskId { get; set; }

        [Required]
        public string Title { get; set; }

        [Required]
        public string StartDate { get; set; }

        [Required]
        public string EndDate { get; set; }

        [Required]
        public string Description { get; set; }

        [Required]
        public string Reward { get; set; }

        [Required]
        public int MinRaiting { get; set; }

        [Required]
        public int MinTasksCompleted { get; set; }

        public bool IsCompleted { get; set; }


        [Required]
        [ForeignKey("Location")]
        public int LocationId { get; set; }
        public virtual Location Location { get; set; }

        [Required]
        [ForeignKey("Creator")]
        public int CreatorId { get; set; }
        public virtual User Creator { get; set; }
    }
}