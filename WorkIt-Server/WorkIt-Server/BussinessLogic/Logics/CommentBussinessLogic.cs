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
        private WorkItDbContext db;

        public CommentBussinessLogic(WorkItDbContext db)
        {
            this.Db = db;
        }

        public WorkItDbContext Db
        {
            get
            {
                return this.db;
            }
            set
            {
                this.db = value;
            }
        }

        public IEnumerable<CommentDTO> GetCommentsById(int id)
        {
            return
                Db.Comments.Join(Db.JobComments,
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

        public bool DeleteCommentById(int jobId, int commentId)
        {
            var commentToDelete =
                Db.Comments.Join(Db.JobComments,
                c => c.CommentId,
                jc => jc.CommentId,
                (c, jc) => new Comment
                {
                    CommentId = c.CommentId,
                    AuthorId = c.AuthorId,
                    Message = c.Message
                })
                .Where(r => r.CommentId == commentId)
                .FirstOrDefault();

            Db.Comments.Remove(commentToDelete);
            Db.SaveChanges();

            return true;
        }
    }
}