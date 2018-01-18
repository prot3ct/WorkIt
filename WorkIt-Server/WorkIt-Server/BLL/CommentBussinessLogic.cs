using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class CommentBussinessLogic
    {
        public IEnumerable<CommentDTO> GetCommentsById(WorkItDbContext db, int id)
        {
            return
                db.Comments.Join(db.JobComments,
                c => c.CommentId,
                jc => jc.CommentId,
                (c, jc) => new CommentDTO {
                    JobId = jc.JobId,
                    AuthorId = c.AuthorId,
                    Message = c.Message
                })
                .Where(r => r.JobId == id)
                .ToList();
        }
    }
}