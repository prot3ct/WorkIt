using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class Raiting
    {
        [Key]
        public int RaitingId { get; set; }

        [Required]
        [ForeignKey("GiverUser")]
        public int GiverUserId { get; set; }
        public virtual User GiverUser { get; set; }

        [Required]
        [ForeignKey("ReceiverUser")]
        public int ReceiverUserId { get; set; }
        public virtual User ReceiverUser { get; set; }

        [Required]
        [ForeignKey("Task")]
        public int TaskId { get; set; }
        public virtual Task Task { get; set; }
    }
}