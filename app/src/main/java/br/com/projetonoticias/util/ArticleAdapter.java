public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> listaArtigos;

    public ArticleAdapter(List<Article> listaArtigos) {
        this.listaArtigos = listaArtigos;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View minhaView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        ArticleViewHolder viewHolder = new ArticleViewHolder(minhaView);
        return viewHolder;
    }


